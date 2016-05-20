package com.nepxion.thunder.framework.parser;

/**
 * <p>Title: Nepxion Thunder</p>
 * <p>Description: Nepxion Thunder For Distribution</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.nepxion.thunder.common.compression.CompressorFactory;
import com.nepxion.thunder.common.constant.ThunderConstants;
import com.nepxion.thunder.common.delegate.ThunderDelegate;
import com.nepxion.thunder.common.entity.ApplicationEntity;
import com.nepxion.thunder.common.entity.PropertyType;
import com.nepxion.thunder.common.entity.RegistryEntity;
import com.nepxion.thunder.common.entity.RegistryType;
import com.nepxion.thunder.common.object.ObjectPoolFactory;
import com.nepxion.thunder.common.property.ThunderProperties;
import com.nepxion.thunder.common.property.ThunderPropertiesManager;
import com.nepxion.thunder.common.serialization.SerializerFactory;
import com.nepxion.thunder.common.smtp.SmtpEventFactory;
import com.nepxion.thunder.common.thread.ThreadPoolFactory;
import com.nepxion.thunder.framework.bean.RegistryFactoryBean;
import com.nepxion.thunder.framework.exception.FrameworkException;
import com.nepxion.thunder.protocol.ProtocolEventFactory;
import com.nepxion.thunder.protocol.redis.cluster.RedisClusterFactory;
import com.nepxion.thunder.protocol.redis.sentinel.RedisSentinelPoolFactory;
import com.nepxion.thunder.registry.RegistryExecutor;
import com.nepxion.thunder.registry.RegistryInitializer;

public class RegistryBeanDefinitionParser extends AbstractExtensionBeanDefinitionParser {
    private static final Logger LOG = LoggerFactory.getLogger(RegistryBeanDefinitionParser.class);
    
    public RegistryBeanDefinitionParser(ThunderDelegate delegate) {
        super(delegate);
    }
    
    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        super.doParse(element, parserContext, builder);
        
        String typeAttributeName = ThunderConstants.TYPE_ATTRIBUTE_NAME;
        String addressAttributeName = ThunderConstants.ADDRESS_ATTRIBUTE_NAME;
        String addressParameterName = ThunderConstants.REGISTRY_ADDRESS_PARAMETER_NAME;
        String configAttributeName = ThunderConstants.CONFIG_ATTRIBUTE_NAME;
        
        String type = element.getAttribute(typeAttributeName);        
        RegistryType registryType = null;
        if (StringUtils.isNotEmpty(type)) {
            registryType = RegistryType.fromString(type);
        } else {
            registryType = RegistryType.ZOOKEEPER;
        }
        
        LOG.info("Registry type is {}", registryType);
        
        // 通过-DThunderRegistryAddress获取值
        String address = System.getProperty(addressParameterName);
        if (StringUtils.isEmpty(address)) {
            address = element.getAttribute(addressAttributeName);
            if (StringUtils.isEmpty(address)) {
                throw new FrameworkException("Registry address is null");
            }
        }
        
        LOG.info("Registry address is {}", address);
        
        String config = element.getAttribute(configAttributeName);
        PropertyType propertyType = null;
        if (StringUtils.isNotEmpty(config)) {
            propertyType = PropertyType.fromString(config);
        } else {
            propertyType = PropertyType.REMOTE;
        }
        
        LOG.info("Property type is {}", propertyType);
        
        RegistryEntity registryEntity = new RegistryEntity();
        registryEntity.setType(registryType);
        registryEntity.setAddress(address);
        registryEntity.setPropertyType(propertyType);
                
        cacheContainer.setRegistryEntity(registryEntity);
        builder.addPropertyValue(createBeanName(RegistryEntity.class), registryEntity);

        RegistryInitializer registryInitializer = createRegistryInitializer(registryType);
        builder.addPropertyValue(createBeanName(RegistryInitializer.class), registryInitializer);
        
        RegistryExecutor registryExecutor = createRegistryExecutor(registryType);
        builder.addPropertyValue(createBeanName(RegistryExecutor.class), registryExecutor);
        
        try {
            initializeRegistry(registryInitializer, registryExecutor, registryEntity);
        } catch (Exception e) {
            LOG.error("Initialize registry center failed", e);
        }
        
        try {
            registerConfiguration(registryExecutor, registryEntity);
        } catch (Exception e) {
            LOG.error("Register configuration failed", e);
        }
        
        initializeFactory();
    }
    
    protected RegistryInitializer createRegistryInitializer(RegistryType registryType) {
        RegistryInitializer registryInitializer = executorContainer.getRegistryInitializer();
        if (registryInitializer == null) {
            String zookeeperRegistryInitializerId = ThunderConstants.ZOOKEEPER_REGISTRY_INITIALIZER_ID;
            try {
                switch (registryType) {
                    case ZOOKEEPER:
                        registryInitializer = createDelegate(zookeeperRegistryInitializerId);
                        break;
                }
            } catch (Exception e) {
                throw new FrameworkException("Creat RegistryInitializer failed", e);
            }
            
            executorContainer.setRegistryInitializer(registryInitializer);
        }
        
        return registryInitializer;
    }
    
    protected RegistryExecutor createRegistryExecutor(RegistryType registryType) {
        RegistryExecutor registryExecutor = executorContainer.getRegistryExecutor();
        if (registryExecutor == null) {
            String zookeeperRegistryExecutorId = ThunderConstants.ZOOKEEPER_REGISTRY_EXECUTOR_ID;
            try {
                switch (registryType) {
                    case ZOOKEEPER:
                        registryExecutor = createDelegate(zookeeperRegistryExecutorId);
                        break;
                }
            } catch (Exception e) {
                throw new FrameworkException("Creat RegistryExecutor failed", e);
            }
            
            executorContainer.setRegistryExecutor(registryExecutor);
        }
        
        return registryExecutor;
    }
    
    // 初始化注册中心连接
    protected void initializeRegistry(RegistryInitializer registryInitializer, RegistryExecutor registryExecutor, RegistryEntity registryEntity) throws Exception {
        // 启动和注册中心的连接
        registryInitializer.start(registryEntity);
        registryExecutor.setRegistryInitializer(registryInitializer);
    }
    
    // 注册属性配置
    protected void registerConfiguration(RegistryExecutor registryExecutor, RegistryEntity registryEntity) throws Exception {
        ApplicationEntity applicationEntity = cacheContainer.getApplicationEntity();
        
        // 初始化注册中心Configuration相关环境
        registryExecutor.registerConfigurationEnvironment();
        
        // 注册Configuration
        registryExecutor.registerConfiguration(applicationEntity);
        
        // 持久化Property文本配置信息
        String property = registryExecutor.retrieveProperty(applicationEntity);
        if (StringUtils.isEmpty(property)) {
            ThunderProperties extProperties = ThunderPropertiesManager.getExtProperties();
            if (extProperties != null) {
                property = extProperties.getContent();

                registryExecutor.persistProperty(property, applicationEntity);
            } else {
                LOG.warn("Local property configs are null, persistence is failed, ignore");
            }
        }
        
        PropertyType propertyType = registryEntity.getPropertyType();
        if (propertyType == PropertyType.REMOTE) {
            if (StringUtils.isNotEmpty(property)) {
                ThunderProperties remoteProperties = new ThunderProperties(property.getBytes());
                
                LOG.info("Merge remote property configs to default property configs");
                LOG.info("---------------- Remote Property Config ----------------\r\n{}", remoteProperties.getContent());
                LOG.info("--------------------------------------------------------");
                
                try {
                    properties.mergeProperties(remoteProperties);
                } catch (Exception e) {
                    LOG.warn("Merge remote property configs failed", e);
                }
            } else {
                LOG.warn("Remote property configs are null, use default configs");
            }
        }
    }
    
    // 初始化工厂
    protected void initializeFactory() {
        ObjectPoolFactory.initialize(properties);
        ThreadPoolFactory.initialize(properties);
        SerializerFactory.initialize(properties);
        CompressorFactory.initialize(properties);
        ProtocolEventFactory.initialize(properties);
        SmtpEventFactory.initialize(properties);
        RedisSentinelPoolFactory.initialize(properties);
        RedisClusterFactory.initialize(properties);
    }
    
    @Override
    protected Class<?> getBeanClass(Element element) {
        return RegistryFactoryBean.class;
    }
}
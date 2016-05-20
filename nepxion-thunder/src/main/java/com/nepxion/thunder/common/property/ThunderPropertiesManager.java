package com.nepxion.thunder.common.property;

/**
 * <p>Title: Nepxion Thunder</p>
 * <p>Description: Nepxion Thunder For Distribution</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThunderPropertiesManager {
    private static final Logger LOG = LoggerFactory.getLogger(ThunderPropertiesManager.class);
    private static final String DEFAULT_PATH = "thunder.properties";
    private static final String EXT_PATH = "thunder-ext.properties";

    private static ThunderProperties properties;
    private static ThunderProperties extProperties;

    static {
        initializeDefaultProperties();
        initializeExtProperties();
    }

    private static void initializeDefaultProperties() {
        try {
            LOG.info("Parse default property config file [{}]", DEFAULT_PATH);

            properties = new ThunderProperties(DEFAULT_PATH);
        } catch (Exception e) {
            LOG.error("Parse default property config file failed for [{}]", DEFAULT_PATH, e);
            e.printStackTrace();
        }
    }

    private static void initializeExtProperties() {
        try {
            LOG.info("Parse ext property config file [{}]", EXT_PATH);

            extProperties = new ThunderProperties(EXT_PATH);
        } catch (Exception e) {
            LOG.warn("Parse ext property config file failed for [{}], maybe file doesn't exist, ignore", EXT_PATH);
        }

        if (properties != null && extProperties != null) {
            LOG.info("Merge ext property configs of [{}] to default property configs", EXT_PATH);

            try {
                properties.mergeProperties(extProperties);
            } catch (Exception e) {
                LOG.warn("Merge ext property configs failed", e);
            }
        }
    }

    public static ThunderProperties getProperties() {
        return properties;
    }
    
    public static ThunderProperties getExtProperties() {
        return extProperties;
    }
}
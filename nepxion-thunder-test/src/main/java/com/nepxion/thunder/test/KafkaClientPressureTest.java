package com.nepxion.thunder.test;

/**
 * <p>Title: Nepxion Thunder</p>
 * <p>Description: Nepxion Thunder For Distribution</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: Nepxion</p>
 * @author Neptune
 * @email 1394997@qq.com
 * @version 1.0
 */

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nepxion.thunder.test.service.EchoFactory;
import com.nepxion.thunder.test.service.EchoService;

public class KafkaClientPressureTest extends AbstractJavaSamplerClient {
    private static final ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:kafka-client-context-pressure.xml");
    private EchoService echoService;
    
    @Override
    public void setupTest(JavaSamplerContext context) {
        echoService = (EchoService) ctx.getBean("echoService");
    }

    @Override
    public SampleResult runTest(JavaSamplerContext context) {
        SampleResult result = new SampleResult();
        result.sampleStart();
        
        try {
            echoService.test250Byte(EchoFactory.create250Byte());
            result.setSuccessful(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccessful(false);
        }
        result.sampleEnd();

        return result;
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {

    }
    
    public static void main(String[] args) {
        new KafkaClientPressureTest().runTest(null);
    }
}
package com.nepxion.thunder.test;

/**
 * <p>Title: Nepxion Thunder</p>
 * <p>Description: Nepxion Thunder For Distribution</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nepxion.thunder.test.service.EchoFactory;
import com.nepxion.thunder.test.service.EchoService;

public class ThunderClientTest {    
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:netty-client-context-pressure.xml");
        EchoService echoService = (EchoService) ctx.getBean("echoService");
        for (int i = 0; i < 1; i++) {
            System.out.println(echoService.test250Byte(EchoFactory.create250Byte()));
            System.out.println(echoService.test2496Byte(EchoFactory.create2496Byte()));
            System.out.println(echoService.test53518Byte(EchoFactory.create53518Byte()));
            System.out.println(echoService.test210748Byte(EchoFactory.create210748Byte()));
            // System.out.println(echoService.test1041538Byte(EchoFactory.create1041538Byte()));
        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
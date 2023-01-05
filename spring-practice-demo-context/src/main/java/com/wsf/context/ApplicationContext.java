package com.wsf.context;

import com.wsf.context.config.InterceptorConfig;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

public class ApplicationContext {

    public static void main(String[] args) {

        //ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //UserService bean = applicationContext.getBean(UserService.class);
        //System.out.println(bean);

        AnnotationConfigServletWebServerApplicationContext applicationContext1 = new AnnotationConfigServletWebServerApplicationContext(InterceptorConfig.class);
        applicationContext1.start();
        StringRedisTemplate bean = applicationContext1.getBean(StringRedisTemplate.class);
        System.out.println(bean);
        bean.opsForValue().set("k1", "v1");

    }
}

package com.wsf.context;

import com.wsf.context.service.UserService;
import com.wsf.context.service.UserService2;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContext {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserService bean = applicationContext.getBean(UserService.class);
        System.out.println(bean);

        UserService2 bean2 = applicationContext.getBean(UserService2.class);
        System.out.println(bean2);


    }
}

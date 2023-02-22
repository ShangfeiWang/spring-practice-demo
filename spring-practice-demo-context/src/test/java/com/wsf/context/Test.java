package com.wsf.context;

import com.wsf.context.service.TestLazyInit;
import com.wsf.context.service.UserService;
import com.wsf.context.service.UserService2;
import com.wsf.context.service.UserService4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

public class Test {



    public static void main(String[] args) {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        testBeanName(applicationContext);
//        testParent(applicationContext);
//        testAbstract(applicationContext);
//        testLazyInit(applicationContext);
//        testFactoryBean(applicationContext);
//        testConstructor(applicationContext);

        String[] strings = StringUtils.tokenizeToStringArray("hello1", ",;");
        for (String string : strings) {
            System.out.println(string);
        }

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    }

    public static void testConstructor(ApplicationContext applicationContext) {
        UserService4 bean = applicationContext.getBean(UserService4.class);
        System.out.println(bean);
    }

    public static void testFactoryBean(ApplicationContext applicationContext) {
        UserService2 factoryCreateBean = applicationContext.getBean(UserService2.class);
        System.out.println(factoryCreateBean);
    }

    public static void testLazyInit(ApplicationContext applicationContext) {
        TestLazyInit bean = applicationContext.getBean(TestLazyInit.class);
        System.out.println(bean);
    }

    public static void testAbstract(ApplicationContext applicationContext) {
        boolean bean = applicationContext.containsBean("parent");
        // 包含BeanDefinition 但是这个bean没有被实例化
        System.out.println("包含Parent: " + bean);
    }

    public static void testParent(ApplicationContext applicationContext) {
        UserService userService = (UserService) applicationContext.getBean("userService");
        System.out.println(userService.getName());
        System.out.println(userService.getAge());
    }

    public static void testBeanName(ApplicationContext applicationContext) {
        Object userService = applicationContext.getBean("userService");
        Object userService2 = applicationContext.getBean("userService2");
        System.out.println(userService == userService2);
    }
}

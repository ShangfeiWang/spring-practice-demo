package com.wsf.spring.applicationcontextinitializer.holder;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }


    public static <T> T getBean(Class<T> beanClass) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(beanClass);
    }

    public static <T> T getBean(String beanName, Class<T> beanClass) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(beanName, beanClass);
    }
}

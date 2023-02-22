package com.wsf.spring.beanpostprocessor.postprocessor;

import com.wsf.spring.beanpostprocessor.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author wsf
 * @since 20221027
 */
@Slf4j
@Component
public class TestInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor {

    @Autowired
    private FactoryBean<User> factoryBean;

    // 实例化之前执行
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            log.info("TestInstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation......");
            try {
                User object = factoryBean.getObject();
                System.out.println(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    // 实例化之后执行
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            log.info("TestInstantiationAwareBeanPostProcessor#postProcessAfterInstantiation......");
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }

    // 属性赋值之前执行
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            log.info("TestInstantiationAwareBeanPostProcessor#postProcessProperties......");
            MutablePropertyValues propertyValues = new MutablePropertyValues();
            propertyValues.add("name", "zhangsan");
            return propertyValues;
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }

    // 初始化之前执行
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            log.info("TestInstantiationAwareBeanPostProcessor#postProcessBeforeInitialization......");
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    // 初始化之后执行
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            log.info("TestInstantiationAwareBeanPostProcessor#postProcessAfterInitialization......");
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        log.info("bean销毁后执后执行..........");
    }
}

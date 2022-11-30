package com.wsf.spring.beanpostprocessor.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author wsf
 * @since 20221027
 */
@Slf4j
@Component
public class TestBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            log.info("TestBeanPostProcessor#postProcessBeforeInitialization......");
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            log.info("TestBeanPostProcessor#postProcessAfterInitialization.......");
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}

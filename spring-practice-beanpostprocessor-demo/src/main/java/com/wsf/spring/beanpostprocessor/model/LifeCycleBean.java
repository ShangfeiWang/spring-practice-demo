package com.wsf.spring.beanpostprocessor.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author wsf
 * @since 20221124
 */
@Slf4j
@Component
public class LifeCycleBean implements InitializingBean, BeanFactoryAware {

    public LifeCycleBean() {
        log.info("构造方法");
    }

    private String port;

    @Autowired
    public void autowire(@Value("${server.port}") String port) {
        log.info("autowire....port:{}", port);
        this.port = port;
    }

    @PostConstruct
    public void init() {
        log.info("init....");
    }

    @PreDestroy
    public void destroy() {
        log.info("destroy...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet...");
    }

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("aware接口方法回调........");
        this.beanFactory = beanFactory;
    }
}
package com.wsf.spring.beanfactorypostprocessor;

import com.wsf.spring.beanfactorypostprocessor.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wsf
 * @since 20221027
 */
public class TestPostProcessor {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

    }

}

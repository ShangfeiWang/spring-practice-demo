package com.wsf.spring.applicationcontextinitializer.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author wsf
 * @since 20221027
 */
@Component
public class TestApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("initialize.....");
    }
}

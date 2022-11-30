package com.wsf.spring.beanfactorypostprocessor;

import com.wsf.spring.beanfactorypostprocessor.model.User;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author wsf
 * @since 20221027
 */
public class GenericApplicationContextPostProcessor {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();
        beanFactory.registerBeanDefinition("config", beanDefinition);
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        Map<String, BeanFactoryPostProcessor> beansOfType = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (Map.Entry<String, BeanFactoryPostProcessor> entry : beansOfType.entrySet()) {
            entry.getValue().postProcessBeanFactory(beanFactory);
        }
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

    }

}

@Configuration
class Config {

    @Bean
    public User user() {
        return new User();
    }

}

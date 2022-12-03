package com.wsf.testimport;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class TestImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        boolean b = registry.containsBeanDefinition("com.wsf.testimport.Blue");
        boolean b2 = registry.containsBeanDefinition("com.wsf.testimport.Red");
        if (b && b2) {
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Black.class);
            registry.registerBeanDefinition("black", rootBeanDefinition);
        }
    }

}

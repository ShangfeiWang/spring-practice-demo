package com.wsf.spring;

import com.wsf.testimport.ImportConfig;
import com.wsf.testimport.TestImportBeanDefinitionRegistrar;
import com.wsf.testimport.TestImportSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @Import 注解可以导入 @Configuration的类、实现了ImportSelector的类，包括实现了ImportBeanDefinitionRegistrar的类
 * 相当于Spring xml配置文件中的<import />标签
 */
@Component
@Import({ImportConfig.class, TestImportSelector.class, TestImportBeanDefinitionRegistrar.class})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class);
        for (String beanDefinitionName : run.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

    }
}

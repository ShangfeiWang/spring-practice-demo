package com.wsf.spring.cycledepency;

import com.wsf.spring.cycledepency.service.AService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class);
        AService bean = run.getBean(AService.class);
        bean.print();
    }
}

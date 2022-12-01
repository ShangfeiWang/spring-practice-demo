package com.wsf.spring.applicationcontextinitializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wsf
 * @since 20221027
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        //springApplication.addInitializers(new TestApplicationContextInitializer());
        springApplication.run(args);
    }

}

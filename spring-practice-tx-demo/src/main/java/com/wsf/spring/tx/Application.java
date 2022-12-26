package com.wsf.spring.tx;

import com.wsf.spring.tx.config.Config;
import com.wsf.spring.tx.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * @author wsf
 * @since 20221222
 */
public class Application {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        UserService bean = applicationContext.getBean(UserService.class);
        bean.insert();
        applicationContext.close();
    }

}

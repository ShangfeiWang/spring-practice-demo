package com.wsf.spring.aop;


import com.wsf.spring.aop.config.Config;
import com.wsf.spring.aop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TxTest {

    @Test
    public void testTx() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        UserService bean = context.getBean(UserService.class);
        bean.insert();
        context.close();
    }
}

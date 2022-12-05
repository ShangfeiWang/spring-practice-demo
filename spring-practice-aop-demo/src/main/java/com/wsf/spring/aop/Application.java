package com.wsf.spring.aop;

import com.wsf.spring.aop.service.TxService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan("com.wsf.spring.aop.dao")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class);
//        UserService bean = run.getBean(UserService.class);
//        bean.insert();

        TxService bean = run.getBean(TxService.class);
        bean.testCommit();
        run.close();
    }
}

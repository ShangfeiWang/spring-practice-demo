package com.wsf.spring.aop;

import com.wsf.spring.aop.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class);
        UserService bean = run.getBean(UserService.class);
        bean.insert();
        run.close();
        //
        ////        TxService bean = run.getBean(TxService.class);
        ////        bean.testCommit();
        //        run.close();

        //SpringApplication.run(Application.class);
    }
}

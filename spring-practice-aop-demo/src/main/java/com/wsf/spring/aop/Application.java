package com.wsf.spring.aop;

import com.wsf.spring.aop.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;


//@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass = false)
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class);
        UserService bean = run.getBean(UserService.class);
        bean.insert();

//        TxService bean = run.getBean(TxService.class);
//        bean.testCommit();
        run.close();
    }
}

package com.wsf.spring.cycledepency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@EnableTransactionManagement
@Component
public class AService {

    @Autowired
    private BService bService;

    //@Transactional
    public void print() {
        System.out.println(bService);
    }

}

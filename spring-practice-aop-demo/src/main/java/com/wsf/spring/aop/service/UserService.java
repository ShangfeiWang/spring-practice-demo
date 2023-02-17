package com.wsf.spring.aop.service;

import com.wsf.spring.aop.aspect.HelloAspect;

/**
 * @author wsf
 * @since 20221124
 */
public interface UserService {


    void insert();

    @HelloAspect
    void deleteUser();
}

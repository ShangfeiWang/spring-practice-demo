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

    /**
     * 测试方法内部调用方法切面不生效解决方案：
     * 1.使用 @EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true) exposeProxy属性设置为true
     * 2.(UserService) AopContext.currentProxy() 获取代理对象
     *
     * 为什么内部方法调用增强逻辑不生效，因为调用到方法内部后，使用的不再是代理对象，而是我们原来的bean对象
     */
    @HelloAspect
    void testInnerCall();
}

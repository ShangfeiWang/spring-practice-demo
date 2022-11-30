package com.wsf.spring.aop.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wsf
 * @since 20221027
 */

@Documented   //该注解表示支持javaDoc文档导出
@Retention(RetentionPolicy.RUNTIME) //该注解表示生命周期
@Target(ElementType.METHOD)  //该注解表示自定义的注解可以使用的对象
public @interface HelloAspect {

}

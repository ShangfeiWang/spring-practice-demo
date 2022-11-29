package com.wsf.spring.aop.dao;


import com.wsf.spring.aop.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    void insert(@Param("user") User user);
}

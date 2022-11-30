package com.wsf.spring.aop.service;

import com.wsf.spring.aop.aspect.HelloAspect;
import com.wsf.spring.aop.dao.UserDao;
import com.wsf.spring.aop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author wsf
 * @since 20221124
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @HelloAspect
    @Transactional(rollbackFor = Exception.class)
    public void insert() {
        User user = new User();
        user.setUsername(UUID.randomUUID().toString().replace("-", ""));
        user.setAge(1);
        userDao.insert(user);
        int i = 1 / 0;
    }

    @HelloAspect
    public void deleteUser() {
        System.out.println("删除user.......");
    }
}

package com.wsf.spring.aop.service.impl;

import com.wsf.spring.aop.aspect.HelloAspect;
import com.wsf.spring.aop.model.User;
import com.wsf.spring.aop.service.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    //@Autowired
    //private UserDao userDao;

    @HelloAspect
    //@Transactional(rollbackFor = Exception.class)
    public void insert() {
        User user = new User();
        user.setUsername(UUID.randomUUID().toString().replace("-", ""));
        user.setAge(1);
        System.out.println("hello");
        //userDao.insert(user);
        //int i = 1 / 0;
    }

    @HelloAspect
    public void deleteUser() {
        System.out.println("删除user.......");
    }
}

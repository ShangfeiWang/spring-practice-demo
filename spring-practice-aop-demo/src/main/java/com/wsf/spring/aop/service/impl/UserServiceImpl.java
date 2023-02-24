package com.wsf.spring.aop.service.impl;

import com.wsf.spring.aop.aspect.HelloAspect;
import com.wsf.spring.aop.model.User;
import com.wsf.spring.aop.service.UserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author wangshangfei
 */
@Service
public class UserServiceImpl implements UserService {

    //@Autowired
    //private UserDao userDao;

    @HelloAspect
    //@Transactional(rollbackFor = Exception.class)
    @Override
    public void insert() {
        User user = new User();
        user.setUsername(UUID.randomUUID().toString().replace("-", ""));
        user.setAge(1);
        System.out.println("hello");
        //userDao.insert(user);
        //int i = 1 / 0;
    }

    @HelloAspect
    @Override
    public void deleteUser() {
        System.out.println("删除user.......");
    }

    @HelloAspect
    @Override
    public void testInnerCall() {
        System.out.println("testInnerCall............");
        // 切面内方法调用自己的方法，切面不生效解决方案
        UserService userService = (UserService) AopContext.currentProxy();
        userService.deleteUser();
    }
}

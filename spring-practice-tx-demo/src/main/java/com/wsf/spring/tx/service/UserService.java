package com.wsf.spring.tx.service;

import com.wsf.spring.tx.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wsf
 * @since 20221222
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void insert() {
        userDao.insert();
        int i = 1 / 0;
    }
}

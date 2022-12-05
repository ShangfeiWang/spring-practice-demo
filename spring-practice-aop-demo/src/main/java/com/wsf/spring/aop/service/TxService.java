package com.wsf.spring.aop.service;

import com.wsf.spring.aop.dao.UserDao;
import com.wsf.spring.aop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.util.UUID;

/**
 * 测试手动提交事务
 */

@Service
public class TxService {

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private TransactionDefinition definition;

    @Autowired
    private UserDao userDao;


    public void testCommit() {

        TransactionStatus transaction = platformTransactionManager.getTransaction(definition);

        try {
            User user = new User();
            user.setUsername(UUID.randomUUID().toString().replace("-", ""));
            user.setAge(1);
            userDao.insert(user);
//            int i = 1 / 0;
            platformTransactionManager.commit(transaction);
        } catch (Exception e) {
            platformTransactionManager.rollback(transaction);

        }

    }
}

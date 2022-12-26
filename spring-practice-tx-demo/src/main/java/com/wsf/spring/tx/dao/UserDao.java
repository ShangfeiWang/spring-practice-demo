package com.wsf.spring.tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author wsf
 * @since 20221222
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert() {
        String sql = "INSERT INTO t_user (username, age) values (?, ?)";
        String username = UUID.randomUUID().toString().replace("-", "").substring(5);
        jdbcTemplate.update(sql, username, 1);
    }

}

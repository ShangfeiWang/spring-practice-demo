//package com.wsf.spring.aop.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//
//@MapperScan("com.wsf.spring.aop.dao")
//@ComponentScan("com.wsf.spring.aop")
//@Configuration
////@EnableAspectJAutoProxy
////@EnableTransactionManagement
//public class Config {
//
//    @Bean
//    public DataSource dataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUsername("root");
//        dataSource.setPassword("123456");
//        dataSource.setUrl("jdbc:mysql://124.223.109.220:3306/test");
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        return dataSource;
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource());
//        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/*.xml");
//        sqlSessionFactoryBean.setMapperLocations(resources);
//        return sqlSessionFactoryBean.getObject();
//    }
//
//    @Bean
//    public PlatformTransactionManager platformTransactionManager() {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource());
//        return dataSourceTransactionManager;
//    }
//
//
//}

//package com.example.demo.configurations;
//
//import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//@Configuration
//@ComponentScan("com.example.demo")
//public class JDBCConfiguration {
//    @Bean
//    public DataSource mysqlDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://172.18.14.108:5432/user-realtime");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("");
// 
//        return dataSource;
//    }
//}

package com.xiaoshouwaliang.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 鉴权模块启动类
 */
@SpringBootApplication
@ComponentScan("com.xiaoshouwaliang")
@MapperScan("com.xiaoshouwaliang.**.dao")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }
}

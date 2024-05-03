package com.xiaoshouwaliang.subject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 刷题模块启动类
 */
@SpringBootApplication
@ComponentScan("com.xiaoshouwaliang")
@MapperScan("com.xiaoshouwaliang.**.mapper")
@EnableFeignClients(basePackages = "com.xiaoshouwaliang")
public class SubjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubjectApplication.class);
    }
}

package com.xiaoshouwaliang.practice.server;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 小手WA凉
 * @date 2024-06-01
 */
@SpringBootApplication
@ComponentScan("com.xiaoshouwaliang")
@MapperScan("com.xiaoshouwaliang.**.dao")
@EnableFeignClients(basePackages = "com.xiaoshouwaliang")
public class PracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PracticeApplication.class);
    }
}

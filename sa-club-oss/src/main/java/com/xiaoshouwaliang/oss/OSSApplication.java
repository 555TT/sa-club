package com.xiaoshouwaliang.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * OSS模块启动类
 */
@SpringBootApplication
@ComponentScan("com.xiaoshouwaliang")
public class OSSApplication {
    public static void main(String[] args) {
        SpringApplication.run(OSSApplication.class);
    }
}

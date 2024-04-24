package com.xiaoshouwaliang.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 鉴权模块启动类
 */
@SpringBootApplication
@ComponentScan("com.xiaoshouwaliang")
//@MapperScan("com.xiaoshouwaliang.**.mapper")
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class);
    }
}

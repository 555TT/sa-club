package com.xiaoshouwaliang.subject.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 小手WA凉
 * @date 2024-05-02
 */
@Configuration
public class ThreadPoolConfig {
    @Bean(name = "labelThreadPool")
    public ThreadPoolExecutor getThreadPool(){
        return new ThreadPoolExecutor(6,20,5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(40),new CustomNameThreadFactory("label"),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}

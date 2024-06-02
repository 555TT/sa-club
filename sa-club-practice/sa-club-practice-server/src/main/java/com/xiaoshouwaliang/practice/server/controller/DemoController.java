package com.xiaoshouwaliang.practice.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小手WA凉
 * @date 2024-06-01
 */
@RestController
@RequestMapping("/practice")
public class DemoController {
    @GetMapping("/test")
    public String test(){
        return "666";
    }
}

package com.xiaoshouwaliang.subject.application.controller;


import com.alibaba.fastjson.JSON;
import com.xiaoshouwaliang.subject.common.entity.PageResult;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectInfoEs;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectEsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小手WA凉
 * @date 2024-05-03
 */
@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {
    /*@Resource
    private UserRpc userRpc;
    @Resource
    private SubjectEsService subjectEsService;
    @PostMapping("/feign")
    public UserInfo testFeign(String userName){
        return userRpc.getUserInfo(userName);
    }*/
    @Resource
    private SubjectEsService subjectEsService;
    @GetMapping("/queryHighlight")
    public void queryHighlight(){
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setKeyWord("nacos ");
        PageResult<SubjectInfoEs> subjectInfoEsPageResult = subjectEsService.querySubjectList(subjectInfoEs);
        log.info(JSON.toJSONString(subjectInfoEsPageResult));
    }
}

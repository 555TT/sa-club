package com.xiaoshouwaliang.subject.applicaton.mq;

import com.alibaba.fastjson.JSON;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLikedBO;
import com.xiaoshouwaliang.subject.domain.service.SubjectLikedDomainService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @author 小手WA凉
 * @date 2024-06-02
 */
@Component
@RocketMQMessageListener(topic = "subject-liked",consumerGroup = "subject-group")
public class SubjectLikedConsumer implements RocketMQListener<String> {
    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;

    @Override
    public void onMessage(String message) {
        SubjectLikedBO subjectLikedBO = JSON.parseObject(message, SubjectLikedBO.class);
        subjectLikedDomainService.syncLikedMsg(subjectLikedBO);
    }
}

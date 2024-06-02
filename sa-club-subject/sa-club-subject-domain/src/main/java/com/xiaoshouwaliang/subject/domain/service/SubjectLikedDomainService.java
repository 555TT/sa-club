package com.xiaoshouwaliang.subject.domain.service;

import com.xiaoshouwaliang.subject.common.entity.PageResult;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLikedBO;

/**
 * @author 小手WA凉
 * @date 2024-05-12
 */
public interface SubjectLikedDomainService {

    /**
     * 点赞
     * @param subjectLikedBO
     */
    void addLike(SubjectLikedBO subjectLikedBO);

    /**
     * 当前用户是否点赞当前题目
     * @param subjectId
     * @param userName
     * @return
     */
    Boolean isLiked(Long subjectId,String userName);
    Integer subjectLikedCount(Long subjectId);

    /**
     * 同步点赞信息到数据库
     */
    void syncLiked();


    PageResult<SubjectLikedBO> getSubjectLikedPage(SubjectLikedBO subjectLikedBO);

    /**
     * 通过MQ同步点赞数据
     * @param subjectLikedBO
     */

    void syncLikedMsg(SubjectLikedBO subjectLikedBO);
}

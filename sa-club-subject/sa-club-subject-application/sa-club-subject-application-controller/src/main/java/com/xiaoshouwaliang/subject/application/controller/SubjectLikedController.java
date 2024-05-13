package com.xiaoshouwaliang.subject.application.controller;

import com.google.common.base.Preconditions;
import com.xiaoshouwaliang.auth.api.entity.Result;
import com.xiaoshouwaliang.subject.application.controller.converter.SubjectLikedDTOConverter;
import com.xiaoshouwaliang.subject.application.controller.dto.SubjectInfoDTO;
import com.xiaoshouwaliang.subject.application.controller.dto.SubjectLikedDTO;
import com.xiaoshouwaliang.subject.common.entity.PageResult;
import com.xiaoshouwaliang.subject.common.util.LoginUtil;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLikedBO;
import com.xiaoshouwaliang.subject.domain.service.SubjectLikedDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小手WA凉
 * @date 2024-05-12
 */
@RestController
@RequestMapping("/subjectLiked")
@Slf4j
public class SubjectLikedController {
    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;

    /**
     * 点赞/取消点赞
     * @param subjectLikedDTO
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody SubjectLikedDTO subjectLikedDTO){
        try {
            Preconditions.checkNotNull(subjectLikedDTO.getSubjectId(),"点赞题目id不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getStatus(),"状态不能为空");
            String loginId = LoginUtil.getLoginId();
            subjectLikedDTO.setLikeUserId(loginId);
            Preconditions.checkNotNull(subjectLikedDTO.getLikeUserId(),"点赞人不能为空");
            SubjectLikedBO subjectLikedBO = SubjectLikedDTOConverter.INSTANCE.converterDTOTOBO(subjectLikedDTO);
            subjectLikedDomainService.addLike(subjectLikedBO);
            return Result.ok();
        } catch (Exception e) {
            log.error("SubjectLikedController.add.error:{}",e.getMessage());
            return Result.fail();
        }
    }

    /**
     * 得到我的点赞列表
     * @param subjectLikedDTO
     * @return
     */
    @PostMapping("/getSubjectLikedPage")
    public Result<PageResult<SubjectInfoDTO>> getSubjectLikedPage(@RequestBody SubjectLikedDTO subjectLikedDTO){
        try {
            Preconditions.checkNotNull(subjectLikedDTO.getPageNo(),"页码不能为空");
            Preconditions.checkNotNull(subjectLikedDTO.getPageSize(),"页大小不能为空");
            String loginId = LoginUtil.getLoginId();
            subjectLikedDTO.setLikeUserId(loginId);
            SubjectLikedBO subjectLikedBO = SubjectLikedDTOConverter.INSTANCE.converterDTOTOBO(subjectLikedDTO);
            PageResult<SubjectLikedBO> pageResult=subjectLikedDomainService.getSubjectLikedPage(subjectLikedBO);
            return Result.ok(pageResult);
        } catch (Exception e) {
            log.error("SubjectLikedController.add.error:{}",e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

}

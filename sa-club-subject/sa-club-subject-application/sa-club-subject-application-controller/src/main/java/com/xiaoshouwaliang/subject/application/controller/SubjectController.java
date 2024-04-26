package com.xiaoshouwaliang.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.xiaoshouwaliang.subject.application.controller.converter.SubjectInfoDTOConverter;
import com.xiaoshouwaliang.subject.application.controller.dto.SubjectInfoDTO;
import com.xiaoshouwaliang.subject.common.entity.PageResult;
import com.xiaoshouwaliang.subject.common.entity.Result;
import com.xiaoshouwaliang.subject.domain.entity.SubjectInfoBO;
import com.xiaoshouwaliang.subject.domain.service.SubjectInfoDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author 小手WA凉
 * @date 2024-04-20
 */
@RestController
@RequestMapping("/subject")
@Slf4j
public class SubjectController {
    @Resource
    private SubjectInfoDomainService subjectInfoDomainService;

    /**
     * 新增题目（工厂+策略模式）
     * @param subjectInfoDTO
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO){
        if(log.isInfoEnabled()){
            log.info("SubjectController.add.DOT:{}", JSON.toJSONString(subjectInfoDTO));
        }
        try {
            Preconditions.checkArgument(!StringUtils.isBlank(subjectInfoDTO.getSubjectName()),"题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(), "题目难度不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(), "题目类型不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(), "题目分数不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getCategoryIds())
                    , "分类id不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getLabelIds())
                    , "标签id不能为空");
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.converterDtoToSubjectBO(subjectInfoDTO);
            subjectInfoDomainService.addSubject(subjectInfoBO);
            return Result.ok(true);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 查询题目列表
     * @param subjectInfoDTO
     * @return
     */
    @PostMapping("/getSubjectPage")
    public Result<PageResult<SubjectInfoDTO>> getSubjectPage(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.getSubjectPage.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getCategoryId(), "分类id不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getLabelId(), "标签id不能为空");
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.converterDtoToSubjectBO(subjectInfoDTO);
            PageResult<SubjectInfoBO> resultBO= subjectInfoDomainService.querySubjectPage(subjectInfoBO);
            return Result.ok(resultBO);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
    @PostMapping("/querySubjectInfo")
    public Result<SubjectInfoDTO> getSubjectInfo(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.getSubjectPage.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getId(), "题目id不能为空");
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.converterDtoToSubjectBO(subjectInfoDTO);
            SubjectInfoBO resultBO= subjectInfoDomainService.querySubjectInfo(subjectInfoBO);
            SubjectInfoDTO resultDTO = SubjectInfoDTOConverter.INSTANCE.converterBOToSubjectDTO(resultBO);
            return Result.ok(resultDTO);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
}

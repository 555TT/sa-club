package com.xiaoshouwaliang.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.xiaoshouwaliang.subject.application.controller.converter.SubjectLabelDTOConverter;
import com.xiaoshouwaliang.subject.application.controller.dto.SubjectLabelDTO;
import com.xiaoshouwaliang.subject.common.entity.Result;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLabelBO;
import com.xiaoshouwaliang.subject.domain.service.SubjectLabelDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-18
 */
@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {
    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;

    /**
     * 新增标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO){
        if(log.isInfoEnabled()){
            log.info("SubjectLabelController.add.subjectLabelDTO:{}", JSON.toJSONString(subjectLabelDTO));
        }
        try {
            Preconditions.checkArgument(!StringUtils.isBlank(subjectLabelDTO.getLabelName()),"标签名称不能为空");
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(),"标签所属分类id不能为空");
            Preconditions.checkNotNull(subjectLabelDTO.getSortNum(),"排序Num不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.converterDtoToLabelBO(subjectLabelDTO);
            subjectLabelDomainService.addLabel(subjectLabelBO);
            return Result.ok(true);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 更新标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO){
        if(log.isInfoEnabled()){
            log.info("SubjectLabelController.add.subjectLabelDTO:{}", JSON.toJSONString(subjectLabelDTO));
        }
        try {
            Preconditions.checkNotNull(subjectLabelDTO.getId(),"标签id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.converterDtoToLabelBO(subjectLabelDTO);
            boolean b = subjectLabelDomainService.updateLabel(subjectLabelBO);
            return Result.ok(b);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 通过id删除标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO){
        if(log.isInfoEnabled()){
            log.info("SubjectLabelController.add.subjectLabelDTO:{}", JSON.toJSONString(subjectLabelDTO));
        }
        try {
            Preconditions.checkNotNull(subjectLabelDTO.getId(),"标签id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.converterDtoToLabelBO(subjectLabelDTO);
            boolean b = subjectLabelDomainService.deleteLabel(subjectLabelBO);
            return Result.ok(b);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 根据分类id查询所有标签
     * @param subjectLabelDTO
     * @return
     */
    //TODO 改成通过题目信息的关联表去查
    @PostMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO){
        if(log.isInfoEnabled()){
            log.info("SubjectLabelController.add.subjectLabelDTO:{}", JSON.toJSONString(subjectLabelDTO));
        }
        try {
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(),"标签所属分类id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.converterDtoToLabelBO(subjectLabelDTO);
            List<SubjectLabelBO> listB0= subjectLabelDomainService.queryLabel(subjectLabelBO);
            List<SubjectLabelDTO> subjectLabelDTOS = SubjectLabelDTOConverter.INSTANCE.converterBOlistToDTOlist(listB0);
            return Result.ok(subjectLabelDTOS);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
}

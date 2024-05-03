package com.xiaoshouwaliang.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.xiaoshouwaliang.subject.application.controller.converter.SubjectCategoryDTOConverter;
import com.xiaoshouwaliang.subject.application.controller.dto.SubjectCategoryDTO;
import com.xiaoshouwaliang.subject.common.entity.Result;
import com.xiaoshouwaliang.subject.domain.entity.SubjectCategoryBO;
import com.xiaoshouwaliang.subject.domain.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;
import javax.annotation.Resource;
import java.util.List;

/**
 * 刷题controller
 */
@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {
    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    /**
     * 新增分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(subjectCategoryDTO.getCategoryName()), "分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "分类父级id不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.converterDtoToCategoryBO(subjectCategoryDTO);
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.add.BO:{}", JSON.toJSONString(subjectCategoryBO));
            }
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("SubjectCategoryController.add.error:{}",e.getMessage(),e);
            return Result.fail("新增题目分类失败");
        }
    }


    /**
     * 查询大分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.queryPrimaryCategory.DTO:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.converterDtoToCategoryBO(subjectCategoryDTO);
            List<SubjectCategoryBO> listBo= subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOS = SubjectCategoryDTOConverter.INSTANCE.converterBOlistToDTOlist(listBo);
            return Result.ok(subjectCategoryDTOS);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryPrimaryCategory.error:{}",e.getMessage(),e);
            return Result.fail("查询大分类失败");
        }
    }

    /**'
     * 根据一级分类查询二级分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/queryCategoryByPrimary")
    public Result<List<SubjectCategoryDTO>> queryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try{
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"父id不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.queryPrimaryCategory.DTO:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.converterDtoToCategoryBO(subjectCategoryDTO);
            List<SubjectCategoryBO> listBo= subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOS = SubjectCategoryDTOConverter.INSTANCE.converterBOlistToDTOlist(listBo);
            return Result.ok(subjectCategoryDTOS);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryCategory.error:{}",e.getMessage(),e);
            return Result.fail("根据一级分类查询二级分类失败");
        }
    }

    /**
     * 一次性查询大分类下所有小分类及小分类下的所有标签
     * @param
     * @return
     */
    @PostMapping("/queryCategoryAndLabel")
    public Result<List<SubjectCategoryDTO>> queryCategoryAndLabel(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
//            String loginId = request.getHeader("loginId");
            Long categoryId = subjectCategoryDTO.getId();
            Preconditions.checkNotNull(categoryId,"大分类id不能为空");
            List<SubjectCategoryBO> resultBO= subjectCategoryDomainService.queryCategoryAndLabel(categoryId);
            List<SubjectCategoryDTO> resultDTO = SubjectCategoryDTOConverter.INSTANCE.converterBOlistToDTOlist(resultBO);
            return Result.ok(resultDTO);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryCategoryAndLabel.error:{}",e.getMessage(),e);
            return Result.fail("一次性查询大分类下所有小分类及小分类下的所有标签失败");
        }
    }

    /**
     * 根据id更新分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"id不能为空");
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.queryPrimaryCategory.DTO:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.converterDtoToCategoryBO(subjectCategoryDTO);
            Boolean result= subjectCategoryDomainService.updateCategory(subjectCategoryBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectCategoryController.update.error:{}",e.getMessage(),e);
           return Result.fail("更新分类失败");
        }
    }

    /**
     * 根据id删除分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"id不能为空");
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.queryPrimaryCategory.DTO:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.converterDtoToCategoryBO(subjectCategoryDTO);
            Boolean result= subjectCategoryDomainService.deleteCategory(subjectCategoryBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectCategoryController.delete.error:{}",e.getMessage(),e);
            return Result.fail("删除分类失败");
        }
    }
}

package com.xiaoshouwaliang.subject.domain.service.Impl;

import com.alibaba.fastjson.JSON;
import com.xiaoshouwaliang.subject.common.enums.IsDeletedFlagEnum;
import com.xiaoshouwaliang.subject.domain.converter.SubjectLabelConverter;
import com.xiaoshouwaliang.subject.domain.entity.SubjectLabelBO;
import com.xiaoshouwaliang.subject.domain.service.SubjectLabelDomainService;
import com.xiaoshouwaliang.subject.infra.basic.entity.SubjectLabel;
import com.xiaoshouwaliang.subject.infra.basic.service.SubjectLabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小手WA凉
 * @date 2024-04-19
 */
@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;

    @Override
    public void addLabel(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.converterBoToLabel(subjectLabelBO);
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainServiceImpl.addLabel.POJO:{}", JSON.toJSONString(subjectLabel));
        }
        subjectLabel.setIsDeleted(0);
        subjectLabelService.insert(subjectLabel);
    }

    @Override
    public boolean updateLabel(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.converterBoToLabel(subjectLabelBO);
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainServiceImpl.addLabel.POJO:{}", JSON.toJSONString(subjectLabel));
        }
        return subjectLabelService.update(subjectLabel)>0;
    }

    @Override
    public boolean deleteLabel(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.converterBoToLabel(subjectLabelBO);
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainServiceImpl.addLabel.POJO:{}", JSON.toJSONString(subjectLabel));
        }
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return subjectLabelService.update(subjectLabel)>0;
    }

    @Override
    public List<SubjectLabelBO> queryLabel(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.converterBoToLabel(subjectLabelBO);
        if(log.isInfoEnabled()){
            log.info("SubjectLabelDomainServiceImpl.addLabel.POJO:{}", JSON.toJSONString(subjectLabel));
        }
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectLabel> list=subjectLabelService.queryByCategoryId(subjectLabel);
        return SubjectLabelConverter.INSTANCE.converterPOListToBOList(list);
    }
}

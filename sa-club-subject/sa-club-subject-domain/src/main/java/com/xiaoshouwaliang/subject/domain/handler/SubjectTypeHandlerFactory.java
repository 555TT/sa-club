package com.xiaoshouwaliang.subject.domain.handler;

import com.xiaoshouwaliang.subject.common.enums.SubjectInfoTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小手WA凉
 * @date 2024-04-20
 */
@Component
public class SubjectTypeHandlerFactory implements InitializingBean {
    @Resource
    private List<SubjectTypeHandler> subjectTypeHandlerList;
    private Map<SubjectInfoTypeEnum,SubjectTypeHandler> handlerMap=new HashMap<>();
    public SubjectTypeHandler getHandler(int subjectType){
        return handlerMap.get(SubjectInfoTypeEnum.getByCode(subjectType));
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        for(SubjectTypeHandler subjectTypeHandler:subjectTypeHandlerList){
            handlerMap.put(subjectTypeHandler.getHandlerType(),subjectTypeHandler);
        }
    }
}

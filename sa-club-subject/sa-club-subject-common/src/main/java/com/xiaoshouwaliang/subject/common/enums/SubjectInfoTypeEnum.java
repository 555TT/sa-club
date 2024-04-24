package com.xiaoshouwaliang.subject.common.enums;

import lombok.Getter;

/**
 * @author 小手WA凉
 * @date 2024-04-20
 */
@Getter
public enum SubjectInfoTypeEnum {
    RADIO(1,"单选"),
    MULTIPLE(2,"多选"),
    JUDGE(3,"判断"),
    BRIEF(4,"简答");
    public int code;
    public String desc;
     SubjectInfoTypeEnum(int code,String desc){
         this.code = code;
         this.desc = desc;
    }
    public static SubjectInfoTypeEnum getByCode(int code){
         for(SubjectInfoTypeEnum subjectInfoTypeEnum:SubjectInfoTypeEnum.values()){
             if(subjectInfoTypeEnum.getCode()==code){
                 return subjectInfoTypeEnum;
             }
         }
         return null;
    }
}

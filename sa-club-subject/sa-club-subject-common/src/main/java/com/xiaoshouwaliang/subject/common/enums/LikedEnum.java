package com.xiaoshouwaliang.subject.common.enums;

import lombok.Getter;

/**
 * @author 小手WA凉
 * @date 2024-04-18
 */
@Getter
public enum LikedEnum {
    LIKED(1,"点赞"),
    UN_LIKED(0,"取消点赞");

    public int code;

    public String desc;

    LikedEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static LikedEnum getByCode(int codeVal){
        for(LikedEnum resultCodeEnum : LikedEnum.values()){
            if(resultCodeEnum.code == codeVal){
                return resultCodeEnum;
            }
        }
        return null;
    }
}

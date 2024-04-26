package com.xiaoshouwaliang.auth.common.enums;

import lombok.Getter;

/**
 * @author 小手WA凉
 * @date 2024-04-17
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(200,"成功"),
    FAIL(500,"失败");
    private int code;
    private String desc;
    ResultCodeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }
    public static ResultCodeEnum getByCode(int codeVal){
        for(ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()){
            if(resultCodeEnum.code == codeVal){
                return resultCodeEnum;
            }
        }
        return null;
    }
}

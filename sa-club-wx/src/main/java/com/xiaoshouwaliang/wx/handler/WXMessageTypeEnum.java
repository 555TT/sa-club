package com.xiaoshouwaliang.wx.handler;

/**
 * @author 小手WA凉
 * @date 2024-04-26
 */
public enum WXMessageTypeEnum {
    SUBSCRIBE("event","用户关注"),
    CONTENT("text","接受文本消息");
    private String type;
    private String desc;
    WXMessageTypeEnum(String type,String desc){
        this.type=type;
        this.desc=desc;
    }

    public static WXMessageTypeEnum getByType(String type){
        for(WXMessageTypeEnum wxMessageTypeEnum:WXMessageTypeEnum.values()){
            if(wxMessageTypeEnum.type.equals(type)){
                return wxMessageTypeEnum;
            }
        }
        return null;
    }
}

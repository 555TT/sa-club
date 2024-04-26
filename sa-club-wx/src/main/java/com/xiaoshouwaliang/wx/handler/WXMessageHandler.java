package com.xiaoshouwaliang.wx.handler;

import java.util.Map;

/**
 * @author 小手WA凉
 * @date 2024-04-26
 */
public interface WXMessageHandler {
    WXMessageTypeEnum getMessageType();
    String delMessage(Map<String,String> map);
}

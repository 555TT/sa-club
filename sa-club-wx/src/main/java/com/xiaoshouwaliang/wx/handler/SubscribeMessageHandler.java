package com.xiaoshouwaliang.wx.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**关注事件处理类
 * @author 小手WA凉
 * @date 2024-04-26
 */
@Service
@Slf4j
public class SubscribeMessageHandler implements WXMessageHandler{
    @Override
    public WXMessageTypeEnum getMessageType() {
        return WXMessageTypeEnum.SUBSCRIBE;
    }

    @Override
    public String delMessage(Map<String, String> map) {
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        long createTime = System.currentTimeMillis();
        String content="感谢您的关注，我是小手WA凉！回复“验证码”即可获取登录验证码";
        String xmlContent="<xml><ToUserName><![CDATA["+fromUserName+"]]></ToUserName>\n" +
                "<FromUserName><![CDATA["+toUserName+"]]></FromUserName>\n" +
                "<CreateTime>"+Long.toString(createTime)+"</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA["+content+"]]></Content>\n" +
                "</xml>";
        return xmlContent;
    }
}

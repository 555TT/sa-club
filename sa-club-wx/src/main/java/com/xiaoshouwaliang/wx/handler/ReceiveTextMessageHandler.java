package com.xiaoshouwaliang.wx.handler;

import com.xiaoshouwaliang.wx.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 小手WA凉
 * @date 2024-04-26
 */
@Service
@Slf4j
public class ReceiveTextMessageHandler implements WXMessageHandler{
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public WXMessageTypeEnum getMessageType() {
        return WXMessageTypeEnum.CONTENT;
    }

    @Override
    public String delMessage(Map<String, String> map) {
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        String receiveContent = map.get("Content");
        if(receiveContent.equals("验证码")){
            long createTime = System.currentTimeMillis();
//            int random = new Random().nextInt(1000);
            UUID uuid = UUID.randomUUID();
            String key="loginKey."+uuid;
            redisTemplate.opsForValue().set(key,fromUserName,180, TimeUnit.SECONDS);
            String content="您的验证码为"+uuid+"。有效期为3分钟！";
            String xmlContent="<xml><ToUserName><![CDATA["+fromUserName+"]]></ToUserName>\n" +
                    "<FromUserName><![CDATA["+toUserName+"]]></FromUserName>\n" +
                    "<CreateTime>"+ createTime +"</CreateTime>\n" +
                    "<MsgType><![CDATA[text]]></MsgType>\n" +
                    "<Content><![CDATA["+content+"]]></Content>\n" +
                    "</xml>";
            return xmlContent;
        }
        else {
            long createTime = System.currentTimeMillis();
            String content="输入“验证码”即可获取登录验证码";
            String xmlContent="<xml><ToUserName><![CDATA["+fromUserName+"]]></ToUserName>\n" +
                    "<FromUserName><![CDATA["+toUserName+"]]></FromUserName>\n" +
                    "<CreateTime>"+ createTime +"</CreateTime>\n" +
                    "<MsgType><![CDATA[text]]></MsgType>\n" +
                    "<Content><![CDATA["+content+"]]></Content>\n" +
                    "</xml>";
            return xmlContent;
        }
    }
}

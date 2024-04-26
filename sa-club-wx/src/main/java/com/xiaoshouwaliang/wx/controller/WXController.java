package com.xiaoshouwaliang.wx.controller;

import com.xiaoshouwaliang.wx.handler.WXMessageHandler;
import com.xiaoshouwaliang.wx.handler.WXMessageHandlerFactory;
import com.xiaoshouwaliang.wx.utils.MessageUtil;
import com.xiaoshouwaliang.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 小手WA凉
 * @date 2024-04-26
 */
@RestController
@Slf4j
public class WXController {
    private static final String token = "wxwhrtoken";
    @Resource
    private WXMessageHandlerFactory wxMessageHandlerFactory;
    @GetMapping("callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {
        log.info("get验签请求参数：signature:{}，timestamp:{}，nonce:{}，echostr:{}",
                signature, timestamp, nonce, echostr);
        String shaStr = SHA1.getSHA1(token, timestamp, nonce, "");
        if (signature.equals(shaStr)) {
            return echostr;
        }
        return "unknown";
    }
    @PostMapping(value = "callback", produces = "application/xml;charset=UTF-8")
    public String callback(@RequestBody String requestBody,
            @RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam(value = "msg_signature", required = false) String msgSignature) {
        log.info("接收到微信消息：requestBody：{}", requestBody);
        Map<String, String> map = MessageUtil.parseXml(requestBody);
        String msgType = map.get("MsgType");
        WXMessageHandler handler = wxMessageHandlerFactory.getHandlerByMesType(msgType);
        if(handler==null){
            return "unknown";
        }
        return handler.delMessage(map);
    }
}
/*<xml><ToUserName><![CDATA[gh_c3670e6ea714]]></ToUserName>
<FromUserName><![CDATA[oM2u06s5rUuhWJz3rSvOmnJLPX74]]></FromUserName>
<CreateTime>1714127432</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[666]]></Content>
<MsgId>24540406821966836</MsgId>
</xml>
<xml><ToUserName><![CDATA[gh_c3670e6ea714]]></ToUserName>
<FromUserName><![CDATA[oM2u06s5rUuhWJz3rSvOmnJLPX74]]></FromUserName>
<CreateTime>1714128962</CreateTime>
<MsgType><![CDATA[event]]></MsgType>
<Event><![CDATA[subscribe]]></Event>
<EventKey><![CDATA[]]></EventKey>
</xml>*/

package com.xiaoshouwaliang.wx.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小手WA凉
 * @date 2024-04-26
 */
@Component
public class WXMessageHandlerFactory implements InitializingBean {
    @Resource
    private List<WXMessageHandler> messageHandlers;

    private  Map<WXMessageTypeEnum,WXMessageHandler> map=new HashMap<>();
    public  WXMessageHandler getHandlerByMesType(String type){
        WXMessageTypeEnum byType = WXMessageTypeEnum.getByType(type);
        return map.get(byType);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for(WXMessageHandler wxMessageHandler:messageHandlers){
            map.put(wxMessageHandler.getMessageType(),wxMessageHandler);
        }
    }
}

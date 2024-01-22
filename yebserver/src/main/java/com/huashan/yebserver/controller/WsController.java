package com.huashan.yebserver.controller;


import com.huashan.yebserver.domain.ChatMsg;
import com.huashan.yebserver.domain.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;

/** websocket 在线聊天
 * @author bing  @create 2021/1/20-下午3:37
 */
@Controller
@CrossOrigin
public class WsController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, ChatMsg chatMsg){
        AdminVo adminVo = (AdminVo) authentication.getPrincipal(); // 获取当前用户对象
        chatMsg.setFrom(adminVo.getUsername()); // 登录用户名
        chatMsg.setFormNickName(adminVo.getName()); // 显示用户名
        chatMsg.setDate(LocalDateTime.now());
        // 发送消息 参数：发送给谁，队列名，消息内容
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(),"/queue/chat",chatMsg);

    }
}
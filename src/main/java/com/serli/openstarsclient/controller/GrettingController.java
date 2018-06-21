package com.serli.openstarsclient.controller;

import com.serli.openstarsclient.data.ChatMessage;
import com.serli.openstarsclient.data.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GrettingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ChatMessage greeting(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new ChatMessage("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}

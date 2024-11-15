package com.websocket.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/message") // Maps messages sent to /app/message
    @SendTo("/topic/messages") // Sends to subscribers of /topic/messages
    public String processMessage(String message) {
        return "Server Response: " + message; // Response sent to client
    }
}

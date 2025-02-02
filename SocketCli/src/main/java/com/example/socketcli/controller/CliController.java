package com.example.socketcli.controller;

import com.example.socketcli.dto.Command;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class CliController {

    @MessageMapping("/cli.sendCommand")
    @SendTo("/cli/public")
    public Command sendCommand(@Payload Command command) {
        return command;
    }

    @MessageMapping("/command.addUser")
    @SendTo("/cli/public")
    public Command addUser(@Payload Command command, SimpMessageHeaderAccessor headerAccessor) {
        // Add username to websocket session
        headerAccessor.getSessionAttributes().put("username", command.getSender());
        return command;
    }
}

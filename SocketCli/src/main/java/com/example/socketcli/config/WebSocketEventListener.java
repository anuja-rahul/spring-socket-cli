package com.example.socketcli.config;

import com.example.socketcli.dto.Command;
import com.example.socketcli.dto.CommandType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String userName = (String) accessor.getSessionAttributes().get("username");

        if (userName != null) {
            log.info("Username is {}", userName);
            var command = Command.builder()
                    .type(CommandType.LEAVE)
                    .sender(userName)
                    .build();

            messageTemplate.convertAndSend("/cli/public", command);
        }
    }
}

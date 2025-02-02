package com.example.socketcli.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Command {

    private String command;
    private String sender;
    private CommandType type;
}

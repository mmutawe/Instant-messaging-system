package com.mmutawe.projects.chat.system.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDto {
    private String user;
    private String content;
}

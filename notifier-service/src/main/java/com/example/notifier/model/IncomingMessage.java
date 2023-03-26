package com.example.notifier.model;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IncomingMessage {
    private long userId;
    private long templateId;
    private String serviceSender;
    private String subject;
    private Map<String, String> texts;
}

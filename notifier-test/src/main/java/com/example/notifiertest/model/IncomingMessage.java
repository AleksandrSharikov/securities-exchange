package com.example.notifiertest.model;

import lombok.*;

import java.util.Map;

@Builder
@Getter
@Setter
public class IncomingMessage {
    private long userId;
    private long templateId;
    private String serviceSender;
    private String subject;
    private Map<String, String> texts;
}

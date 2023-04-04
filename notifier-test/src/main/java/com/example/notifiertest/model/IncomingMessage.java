package com.example.notifiertest.model;

import lombok.*;

import java.util.Map;

@Builder
@Getter
@Setter
public class IncomingMessage {
    private Long userId;
    private long templateId;
    private String serviceSender;
    private Map<String, String> texts;
}

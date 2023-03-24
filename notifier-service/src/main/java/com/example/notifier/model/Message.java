package com.example.notifier.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private String serviceSender;
    private String toEmail;
    private String toPhone;
    private String subject;
    private String text;
}

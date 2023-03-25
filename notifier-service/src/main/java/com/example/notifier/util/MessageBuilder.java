package com.example.notifier.util;

import com.example.notifier.model.Message;
import com.example.notifier.model.Template;
import com.example.notifier.model.User;
import lombok.*;

import java.util.Map;

/**
 * Описание
 */
@Builder
public class MessageBuilder {
    private Template template;
    private String serviceSender;
    private Map<String, Object> data;
    private String subject; // TODO возможно тему надо хранить в БД шаблонов
    private User user;

    /**
     * Описание
     */
    private String createTextMessage() {
        String textTemplate = template.getText();
        for (String key : data.keySet()) {
            textTemplate = textTemplate.replace("${" + key + "}", data.get(key).toString());
        }
        return textTemplate;
    }

    public Message newMessage() {
        return Message.builder()
                .serviceSender(this.serviceSender)
                .toEmail(this.user.getEmail())
                .toPhone(this.user.getPhone())
                .subject(this.subject)
                .text(createTextMessage())
                .build();
    }
}

package com.example.notifier.service;

import com.example.notifier.enums.SendingStatus;
import com.example.notifier.model.*;
import lombok.*;

import java.util.Date;

/**
 * MessageBuilder строит новое сообщение из предварительных данных.
 */
@Builder
public class MessageBuilder {
    private Template template;
    private OrderMessage orderMessage;
    private User user;

    /**
     * Метод подставляет в переменные из текста шаблона необходимые данные
     */
    private String createTextMessage() {
        String textTemplate = template.getText();
        for (String key : orderMessage.getTexts().keySet()) {
            textTemplate = textTemplate.replace("${" + key + "}", orderMessage.getTexts().get(key));
        }
        return textTemplate;
    }

    public Message newMessage() {
        return Message.builder()
                .serviceSender(this.orderMessage.getServiceSender())
                .toEmail(this.user.getEmail())
                .toPhone(this.user.getPhone())
                .subject(this.template.getSubject())
                .text(createTextMessage())
                .sendingStatus(SendingStatus.NOT_SENT)
                .dateCreate(new Date())
                .numberTryToSend((byte) 0)
                .build();
    }
}

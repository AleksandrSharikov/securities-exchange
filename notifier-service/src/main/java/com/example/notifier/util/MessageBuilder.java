package com.example.notifier.util;

import com.example.notifier.model.Message;
import com.example.notifier.model.Template;
import com.example.notifier.model.User;
import lombok.*;

import java.util.Map;

/**
 * Данный класс создает сообщение по шаблону с помощью метода newMessage().
 * Описание и требования к шаблону описаны в классе Template.
 */
@Getter
@Builder
public class MessageBuilder {
    private final Template template;
    private final String serviceSender;
    private final Map<String, String> data;
    private final String subject; // TODO возможно тему надо хранить в БД шаблонов
    private final User user;

    /**
     * Метод, который выстраивает сообщение по заданному шаблону.
     * В брокер сообщений должен приходить json, который обязательно имеет id_template.
     * Метод подставляет значения из json в текст из шаблона.
     * Возвращает метод новое сообщение типа String
     */
    private String createTextMessage() {
        String textTemplate = template.getText();
        for (String key : data.keySet()) {
            textTemplate = textTemplate.replace("${" + key + "}", data.get(key));
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

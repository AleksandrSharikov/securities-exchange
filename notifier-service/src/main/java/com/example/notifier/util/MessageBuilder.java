package com.example.notifier.util;

import com.example.notifier.model.Template;
import lombok.*;

import java.util.Map;

/**
 * Данный класс создает сообщение по шаблону с помощью метода newMessage().
 * Описание и требования к шаблону описаны в классе Template.
 */
@Getter
public class MessageBuilder {
    /**
     * Шаблон сообщения
     */
    private final Template template;
    /**
     * Имя сервиса с помощью которого будет отправлено сообщение
     */
    private final String serviceSender;
    /**
     * Коллекция data хранит все поля и их значения, которые требуется вставить в текст
     */
    private final Map<String, String> data;
    /**
     * Тема сообщения
     */
    private final String subject; //TODO возможно тему надо хранить в БД шаблонов
    /**
     * Получатель сообщения
     */
    private final String recipient; //TODO это поле нужно точнее определить - емейл или номер телефона, какие данные и т.п.

    @Builder
    public MessageBuilder(Template template, String serviceSender, Map<String, String> data, String subject, String recipient) {
        this.template = template;
        this.serviceSender = serviceSender;
        this.data = data;
        this.subject = subject;
        this.recipient = recipient;
    }

    /**
     * Метод, который выстраивает сообщение по заданному шаблону.
     * В брокер сообщений должен приходить json, который обязательно имеет id_template.
     * Метод подставляет значения из json в текст из шаблона.
     * Возвращает метод новое сообщение типа String
     */
    public String newMessage() {
        String textTemplate = template.getText();
        for (String key : data.keySet()) {
                textTemplate = textTemplate.replace("${" + key + "}", data.get(key));
        }

        return textTemplate;
    }
}

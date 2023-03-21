package com.example.notifier.util;

import com.example.notifier.model.Template;
import lombok.*;

import java.util.Map;

/**
 * Данный класс создает сообщение по шаблону с помощью метода newMessage().
 * Описание и требования к шаблону описаны в классе Template.
 */
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
    private final Map<String, Object> data;

    @Builder
    public MessageBuilder(Template template, String serviceSender, Map<String, Object> data) {
        this.template = template;
        this.serviceSender = serviceSender;
        this.data = data;
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
            if (!key.equals("id_template")) {
                textTemplate = textTemplate.replace("${" + key + "}", (String) data.get(key));
            }
        }

        return textTemplate;
    }
}

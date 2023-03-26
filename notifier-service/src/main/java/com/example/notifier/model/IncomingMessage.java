package com.example.notifier.model;

import lombok.*;

import java.util.Map;

/**
 * IncomingMessage - модель сообщения, которое принимает Notifier от других сервис для отправки сообщения пользователю.
 * Данное сообщение принимается брокером сообщений.
 */
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

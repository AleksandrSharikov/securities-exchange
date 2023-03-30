package com.example.notifier.model;

import lombok.*;

import java.util.Map;

/**
 *   OrderMessage - модель сообщения, которое принимает Notifier от других сервисов для отправки сообщения пользователю.
 *   Данное сообщение принимается брокером сообщений.
 *
 *  Параметр text содержит пары key-value -> ключ является переменной в тексте шаблона, value значение на которое
 *  требуется заменить переменную.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderMessage {

    @NonNull
    private Long userId;

    @NonNull
    private Long templateId;

    @NonNull
    private String serviceSender;

    @NonNull
    private String subject;

    @NonNull
    private Map<String, String> texts;
}

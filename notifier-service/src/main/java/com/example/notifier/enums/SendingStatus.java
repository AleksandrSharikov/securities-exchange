package com.example.notifier.enums;

/**
 * Список статусов для модели Message
 */
public enum SendingStatus {

    SENT("SENT"), // отправлено
    NOT_SENT("NOT_SENT"), // не отправлено
    DEPRECATED("deprecated"); // устарело и к новой попытке отправки не подлежит

    final String values;

    SendingStatus(String values) {
        this.values = values;
    }
}

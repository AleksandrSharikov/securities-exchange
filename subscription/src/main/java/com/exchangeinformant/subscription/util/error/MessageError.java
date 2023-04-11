package com.exchangeinformant.subscription.util.error;

import java.time.LocalDateTime;

/**
 * Класс для создания описания ошибки.
 */
public class MessageError {

    /**
     * Создает описание ошибки.
     * @param message сообщение об ошибке.
     * @return описание ошибки.
     */
    public String createErrorDescription(final String message) {
        LocalDateTime localDateTime = LocalDateTime.now();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error ( ")
                .append(message)
                .append(", ")
                .append(localDateTime)
                .append(" )");
        return stringBuilder.toString();
    }
}

package com.exchangeinformant.subscription.util.error;

import java.time.LocalDateTime;

public class MessageError {

    public String createErrorDescription(String message) {
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

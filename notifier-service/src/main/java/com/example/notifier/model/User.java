package com.example.notifier.model;

import lombok.*;

/**
 * Модель User. Хранит информацию о пользователе, необходимую для отправки сообщения
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @NonNull
    private Long id;

    private String email;

    private String phone;
}

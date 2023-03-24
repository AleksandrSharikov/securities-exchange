package com.example.notifier.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Данный процессор будет отправлять id юзера на сервис user-profile и получать информацию об юзере, необходимую
 * для отправки сообщений.
 *
 * Так как со стороны user-profile еще нет данного функционала, этот класс будет возвращать тестовые данные
 */
@Component
@Slf4j
public class UserInfoProcessor {
    public String getUserEmail(long id) {
        return "westalex777@yandex.ru"; //TODO
    }

    public String getUserPhone(long id) {
        return "+7-777-777-77-77";
    }
}

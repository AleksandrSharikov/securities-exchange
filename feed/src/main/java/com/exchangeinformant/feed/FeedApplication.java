package com.exchangeinformant.feed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Сервис для получения сообщений и отправки их на ленту для каждого пользователя
 */
@SpringBootApplication
public class FeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedApplication.class, args);
    }

}

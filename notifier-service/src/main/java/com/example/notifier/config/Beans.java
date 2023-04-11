package com.example.notifier.config;

import com.example.notifier.model.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class Beans {

    /**
     *
     * @return Хранилище, которе содержит первичное сообщение полученное из топика 'to-notifier' и ключ, которые
     * используется в сообщении для отправки запроса на User-Profile
     */
    @Bean("storage-inMessageFromOtherServices")
    public Map<String, OrderMessage> createStorageInMessageFromOtherServices() {
        return new HashMap<>();
    }
}

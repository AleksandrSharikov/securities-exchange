package com.example.notifier.config;

import com.example.notifier.model.Message;
import com.example.notifier.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Configuration
public class BeanStorage {

    @Bean("storage-inMessageFromOtherServices")
    public Map<Long, LinkedList<String>> createStorageInMessageFromOtherServices() {
        return new HashMap<>();
    }

    @Bean("storage-user-info")
    public LinkedList<User> createStorageUserInfo() {
        return new LinkedList<>();
    }

    @Bean("order-to-send")
    public LinkedList<Message> createOrderToSend() { // TODO подумать о Queue
        return new LinkedList<>();
    }
}

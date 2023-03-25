package com.example.notifier.config;

import com.example.notifier.processor.MessageManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class ObjectFactoryBean {
    private final ObjectFactory<MessageManager> messageManagerBean;

    public MessageManager getMessageManager() {
        return messageManagerBean.getObject();
    }

    @Bean("storage-inMessageFromOtherServices")
    public Map<String, String> createStorageInMessageFromOtherServices() {
        return new HashMap<>();
    }

    @Bean
    public ObjectMapper createObjectMapperBean() {
        return new ObjectMapper();
    }
}
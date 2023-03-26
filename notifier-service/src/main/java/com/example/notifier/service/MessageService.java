package com.example.notifier.service;

import com.example.notifier.kafka.KafkaProducer;
import com.example.notifier.model.IncomingMessage;
import com.example.notifier.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class MessageService {
    private final ObjectMapper objectMapper;
    private final Map<String, IncomingMessage> storageInMessageFromOtherServices;
    private final KafkaProducer kafkaProducer;

    private String generationKey() {
        Random random = new Random();
        return String.valueOf(random.nextInt());
    }

    public void rederictToUserProfile(String message) {
        String key = generationKey();
        User user = null;
        try {
            IncomingMessage incomingMessage = objectMapper.readValue(message, IncomingMessage.class);
            user = User.builder().id(incomingMessage.getUserId()).build();
            storageInMessageFromOtherServices.put(key, incomingMessage);
            kafkaProducer.sendMessage("request-to-userProfile-from-notifier", key, objectMapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            log.info("outgoing to user-profile -> key: {}, value: {}", key, user);
        }
    }
}

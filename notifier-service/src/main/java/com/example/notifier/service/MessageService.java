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

    public void incomingMessageProcessor(String message) throws JsonProcessingException {
        String key = generationKey();
        IncomingMessage incomingMessage = objectMapper.readValue(message, IncomingMessage.class);
        User user = User.builder().id(incomingMessage.getUserId()).build();
        storageInMessageFromOtherServices.put(key, incomingMessage);
        requestToUserProfile(user, key);
    }

    public void requestToUserProfile(User user, String key) throws JsonProcessingException {
        kafkaProducer.sendMessage("request-to-userProfile-from-notifier", key, objectMapper.writeValueAsString(user));
        log.info("outgoing to user-profile -> key: {}, value: {}", key, user);
    }
}

package com.example.notifier.service;

import com.example.notifier.kafka.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    private final Map<String, String> storageInMessageFromOtherServices;
    private final KafkaProducer kafkaProducer;

    private String generationKey() {
        Random random = new Random();
        return String.valueOf(random.nextInt());
    }

    public void rederictToUserProfile(String message) {
        String key = generationKey();
        ObjectNode json = objectMapper.createObjectNode();
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            long userId = jsonNode.get("userId").asLong();
            json.put("userId", userId);
            storageInMessageFromOtherServices.put(key, message);
            kafkaProducer.sendMessage("request-to-userProfile-from-notifier", key, objectMapper.writeValueAsString(json));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally {
            log.info("outgoing to user-profile -> key: {}, value: {}", key, json);
        }
    }
}

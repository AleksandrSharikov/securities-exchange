package com.example.notifiertest.service;

import com.example.notifiertest.kafka.KafkaProducer;
import com.example.notifiertest.model.IncomingMessage;
import com.example.notifiertest.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class JsonService {
    private final KafkaProducer kafkaProducer;
    private final List<User> listUsers;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private int i = 0;

    public JsonService(KafkaProducer kafkaProducer, List<User> listUsers) {
        this.kafkaProducer = kafkaProducer;
        this.listUsers = listUsers;
    }

    public void response(String key, String message) {
        String json = responseToNotifier(message);
        kafkaProducer.sendMessage("response-userProfile-to-notifier", key, json);
        log.info("outgoing response to notifier -> key: {}, value: {}", key, json);

    }

    public String responseToNotifier(String message) {
        try {
            User user = objectMapper.readValue(message, User.class);
            user = getUserById(user.getId());
            return objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserById(long id) {
        return listUsers.get((int) id - 1);
    }

    @Scheduled(fixedRate = 5000)
    public void generateJson() throws JsonProcessingException {
        i++;
        String json = createJson();
        kafkaProducer.sendMessage("to-notifier", json);
        log.info("outgoing: {}", i);
    }

    private String createJson() {
        Map<String, String> texts = new HashMap<>();
        texts.put("name", "Alex");
        texts.put("number_order", "1000100");
        texts.put("date_delivery", "11 04 2023");

        IncomingMessage message = IncomingMessage.builder()
                .userId(1)
                .templateId(1)
                .serviceSender("Email")
                .subject("Test subject")
                .texts(texts)
                .build();

        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
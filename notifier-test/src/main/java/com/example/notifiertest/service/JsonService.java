package com.example.notifiertest.service;

import com.example.notifiertest.processor.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Component
@Slf4j
public class JsonService {
//    private final KafkaProducer kafkaProducer;
    private final LinkedList<String> jsonCreationEvents;
    private int i = 0;

    public JsonService(LinkedList<String> jsonCreationEvents) {
//        this.kafkaProducer = kafkaProducer;
        this.jsonCreationEvents = jsonCreationEvents;
    }

    @Scheduled(fixedRate = 5000)
    public void generateJson() throws JsonProcessingException {
        i++;
        jsonCreationEvents.add(createJson());
//        if (jsonCreationEvents.peek() != null) {
//            kafkaProducer.sendMessage(i + jsonCreationEvents.peek());
//            log.info("outgoing: {}", i + jsonCreationEvents.poll());
//        }
    }

    private String createJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data = new HashMap<>();
        data.put("template_id", 1);
        data.put("name", "Alex");
        data.put("number_order", "1000356");
        data.put("date_delivery", "11.04.2023");
        data.put("serviceSender", "Email");
        data.put("subject", "Test");
        data.put("user_id", 5);
        return objectMapper.writeValueAsString(data);
    }
}

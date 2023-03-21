package com.example.notifiertest.service;

import com.example.notifiertest.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

@Component
@AllArgsConstructor
@Slf4j
public class JsonService {
    private final LinkedList<String> jsonCreationEvents;

    @Scheduled(fixedRate = 5000)
    public void generateBook() throws JsonProcessingException {
        jsonCreationEvents.add(creatJson());
    }

    private String creatJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data = new HashMap<>();
        data.put("id_template", 1);
        data.put("name", "Alex");
        data.put("number_order", "1000356");
        data.put("date_delivery", "11.04.2023");
        data.put("serviceSender", "Email");
        String jsonString = objectMapper.writeValueAsString(data);
        return jsonString;
    }
}

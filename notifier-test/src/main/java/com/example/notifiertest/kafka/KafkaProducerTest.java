package com.example.notifiertest.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaProducerTest {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerTest(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topicName, String key, String message) {
        kafkaTemplate.send(topicName, key, message);
    }

    public void sendMessage(String topicName, String message) {
        kafkaTemplate.send(topicName, message);
    }
}

package com.example.notifier.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaProducerNotifier {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerNotifier(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topicName, String key, String message) {
        kafkaTemplate.send(topicName, key, message);
    }
}

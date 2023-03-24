package com.example.notifiertest.processor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ListenerKafka {
    private final KafkaProducer kafkaProducer;

    @KafkaListener(id = "fromOtherForSendMessage", topics = "to-userProfile-from-notifier")
    public void listen(String in) {
        log.info("incoming: " + in);
    }
}

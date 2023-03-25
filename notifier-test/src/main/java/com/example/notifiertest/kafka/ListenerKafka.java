package com.example.notifiertest.kafka;

import com.example.notifiertest.service.JsonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
@AllArgsConstructor
public class ListenerKafka {
    private final JsonService jsonService;

    @KafkaListener(id = "fromOtherForSendMessage1", topics = "request-to-userProfile-from-notifier")
    public void fromOtherForSendMessage(ConsumerRecord<byte[], byte[]> record) {
        String key = new String(record.key());
        String value = new String(record.value());
        log.info("incoming from notifier -> key: {}, value {}", key, value);
        jsonService.response(key, value);
    }
}
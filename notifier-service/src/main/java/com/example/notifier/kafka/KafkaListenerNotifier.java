package com.example.notifier.kafka;

import com.example.notifier.processor.OrderMessageProcessor;
import com.example.notifier.processor.MessageProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaListenerNotifier {
    private final OrderMessageProcessor orderMessageProcessor;
    private final MessageProcessor messageProcessor;

    @KafkaListener(id = "fromOtherForSendMessage", topics = "to-notifier")
    public void fromOtherForSendMessage(String inMessage) {
        log.info("incoming: " + inMessage);
        orderMessageProcessor.accept(inMessage);
    }

    @KafkaListener(id = "responseFromUserProfileToNotifier", topics = "response-userProfile-to-notifier")
    public void responseFromUserProfileToNotifier(ConsumerRecord<byte[], byte[]> record) {
        String key = new String(record.key());
        String value = new String(record.value());
        log.info("incoming message from User-Profile -> key: {}, value: {}", key, value);
        messageProcessor.accept(key, value);
    }
}

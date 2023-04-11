package com.example.notifier.kafka;

import com.example.notifier.processor.OrderMessageProcessor;
import com.example.notifier.processor.MessageProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaListenerNotifier {
    private final OrderMessageProcessor orderMessageProcessor;
    private final MessageProcessor messageProcessor;

    @KafkaListener(id = "fromOtherForSendMessage", topics = "to-notifier")
    public void fromOtherForSendMessage(String inMessage) {
        log.info("incoming: " + inMessage);
        Mono.just(inMessage)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(orderMessageProcessor)
                .doOnError(error -> log.error(error.toString()))
                .subscribe();
    }

    @KafkaListener(id = "responseFromUserProfileToNotifier", topics = "response-userProfile-to-notifier")
    public void responseFromUserProfileToNotifier(ConsumerRecord<byte[], byte[]> record) {
        String key = new String(record.key());
        String value = new String(record.value());
        Mono.just(record)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(message -> log.info("incoming message from User-Profile -> key: {}, value: {}", key, value))
                .doOnNext(message -> messageProcessor.accept(key, value))
                .doOnError(error -> log.error(error.toString()))
                .subscribe();
    }
}

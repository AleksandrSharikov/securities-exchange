package com.example.notifier.kafka;

import com.example.notifier.config.ObjectFactoryBean;
import com.example.notifier.processor.MessageManager;
import com.example.notifier.sender.EmailSender;
import com.example.notifier.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@Component
@Slf4j
@AllArgsConstructor
public class ListenerKafka {
    private MessageService messageService;
    private final ObjectFactoryBean objectFactoryBean;
    private final Map<String, String> storageInMessageFromOtherServices;
    private final EmailSender emailSender;

    @KafkaListener(id = "fromOtherForSendMessage", topics = "to-notifier")
    public void fromOtherForSendMessage(String inMessage) {
        log.info("incoming: " + inMessage);
        messageService.rederictToUserProfile(inMessage);
    }

    @KafkaListener(id = "responseFromUserProfileToNotifier", topics = "response-userProfile-to-notifier")
    public void responseFromUserProfileToNotifier(ConsumerRecord<byte[], byte[]> record) {
        String key = new String(record.key());
        String value = new String(record.value());
        log.info("incoming message from User-Profile -> key: {}, value: {}", key, value);
        Flux.just(value)
                .publishOn(Schedulers.boundedElastic())
                .map(x -> {
                    MessageManager messageManager = objectFactoryBean.getMessageManager();
                    return messageManager.apply(value, storageInMessageFromOtherServices.get(key));
                })
                .subscribe(x -> emailSender.sendMessage("ЕМЕЙЛ МОЖНО НАПИСАТЬ ЗДЕСЬ!", x.getSubject(), x.getText()));
    }
}

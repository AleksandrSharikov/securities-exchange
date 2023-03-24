package com.example.notifier.kafka;

import com.example.notifier.model.User;
import com.example.notifier.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
@Slf4j
@AllArgsConstructor
public class ListenerKafka {
    private MessageService messageService;

    @KafkaListener(id = "fromOtherForSendMessage", topics = "to-notifier")
    public void fromOtherForSendMessage(String inMessage) {
        log.info("incoming: " + inMessage);
        messageService.inMessageProcessor(inMessage);
    }

    @KafkaListener(id = "responseFromUserProfileToNotifier", topics = "response-userProfile-to-notifier")
    public void responseFromUserProfileToNotifier(User user) {
        log.info("incoming: " + user);
        messageService.messageManager(user);
    }
}

package com.example.notifier.processor;

import com.example.notifier.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class MessageInProcessor implements Consumer<Message<String>> {
    private final MessageService messageService;

    public MessageInProcessor(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void accept(Message<String> jsonMessage) {
        log.info("Получен json: {}", jsonMessage.getPayload());
        messageService.messageManager(jsonMessage.getPayload());
    }
}

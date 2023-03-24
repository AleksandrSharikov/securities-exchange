package com.example.notifier.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * Подписчик на входящие сообщения от других сервисов, которые заказывают отправку сообщения юзеру
 */
@Component
@Slf4j
public class InMessageFromOtherServices implements Consumer<Message<String>> {
    private final LinkedList<String> storageInMessageFromOtherServices;

    public InMessageFromOtherServices(@Qualifier("storage-inMessageFromOtherServices") LinkedList<String> storageInMessageFromOtherServices) {
        this.storageInMessageFromOtherServices = storageInMessageFromOtherServices;
    }

    @Override
    public void accept(Message<String> jsonMessage) {
        storageInMessageFromOtherServices.add(jsonMessage.getPayload());
        log.info("Incoming message from topic 'message-from-other-services': {}", jsonMessage.getPayload());
    }
}

// Слушатель входной очереди

package com.exchangeinformant.feed.services;

import com.exchangeinformant.feed.DTO.MessageDTO;
import com.exchangeinformant.feed.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
@Component
public class QueueListener {


    private final MessageService messageService;

    @Autowired
    public QueueListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void listener(MessageDTO message) {
        log.info("Message \" {} \" received by listener", message);
        messageService.receiveMessage(message);
    }
}
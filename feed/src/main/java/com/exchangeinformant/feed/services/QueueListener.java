package com.exchangeinformant.feed.services;


import com.exchangeinformant.feed.dto.MessageInDTO;
import com.exchangeinformant.feed.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
@Component
public class QueueListener {

    // Слушатель входной очереди
    private final MessageService messageService;

    @Autowired
    public QueueListener(MessageService messageService) {
        this.messageService = messageService;
    }

    // Получение сообщения и отправка его в сервис для сохранения в БД
    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void listener(MessageInDTO message) {
        log.info("Message \" {} \" received by listener", message);
        messageService.receiveMessage(message);
    }
}
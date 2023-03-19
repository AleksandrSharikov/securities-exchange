package com.exchangeinformant.feed.services;

import com.exchangeinformant.feed.DTO.MessageDTO;
import com.exchangeinformant.feed.config.RabbitConfig;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Component
public class QueueListener {

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void listener(MessageDTO message) {
        System.out.println(message);
    }
}
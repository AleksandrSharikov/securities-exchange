package com.exchangeinformant.feed.controllers;

import com.exchangeinformant.feed.config.RabbitConfig;
import com.exchangeinformant.feed.model.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class MessageController {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody Message message) {
       // message.setId(UUID.randomUUID().toString());
        message.setReceivingTime(LocalDateTime.now());
        template.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.KEY, message);
        return "Message Published";
    }
}

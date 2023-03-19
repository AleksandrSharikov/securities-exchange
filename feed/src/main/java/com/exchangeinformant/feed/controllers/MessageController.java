package com.exchangeinformant.feed.controllers;

import com.exchangeinformant.feed.DTO.MessageDTO;
import com.exchangeinformant.feed.config.RabbitConfig;
import com.exchangeinformant.feed.model.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/feed")
@Tag(name = "Контроллер ленты", description = "Получение и передача сообщений ленты")
public class MessageController {

    @Autowired
    private RabbitTemplate template;


    @Operation(summary = "Получение сообщений для ленты")
    @PostMapping("/publish")
    public String publishMessage(@RequestBody MessageDTO messageDTO) {
       // message.setId(UUID.randomUUID().toString());
      //  Message message = new Message(messageDTO);
       // System.out.println("get");
       // message.setReceivingTime(LocalDateTime.now());
        template.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.KEY, messageDTO);
        log.info("get something");
        return "Message Published";
    }

    @Operation(summary = "Тестовый контроллер")
    @GetMapping("/")
    public String testController(){
        log.info("test controller worked");
        return "test";
    }

}

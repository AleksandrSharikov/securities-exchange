package com.exchangeinformant.feed.controllers;

//Конотроллеры

import com.exchangeinformant.feed.dto.MessageInDTO;
import com.exchangeinformant.feed.dto.MessageOutDTO;
import com.exchangeinformant.feed.services.MessageService;
import com.exchangeinformant.feed.services.StreamListener;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.function.Supplier;

@Slf4j
@RestController
@RequestMapping("/feed")
@Tag(name = "Контроллер ленты", description = "Получение и передача сообщений ленты")
public class MessageController {


    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService ) {
        this.messageService = messageService;
    }

    @Operation(summary = "Контроллер для отправки сообщений в очередь с целью тестирования")
    @PostMapping("/publish")
    public MessageInDTO publishMessage(@RequestBody MessageInDTO messageInDTO) {

        //template.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.KEY, messageInDTO);
        sendMessage(messageInDTO);
        log.info("Post controller get something");
        return messageInDTO;
    }




    @Bean
    public Supplier<MessageInDTO> sendMessage(MessageInDTO message) {
        public Supplier<MessageInDTO> testPutController() {
            return () -> message;   };
        return testPutController;
    }

    @Operation(summary = "Получение ленты для пользователя с определённым id и минимальным rank")
    @GetMapping(value = {"/{id}/{rank}","/{id}"})
    public List<MessageOutDTO> getUsersFeed(@PathVariable long id, @PathVariable(required = false) Integer rank)
    {
        log.info("requested list for user {} ", id);
        return rank == null ? messageService.unreadMessageList(id) : messageService.unreadMessageList(id,rank);
    }

}

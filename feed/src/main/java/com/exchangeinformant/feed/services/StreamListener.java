package com.exchangeinformant.feed.services;


import com.exchangeinformant.feed.controllers.MessageController;
import com.exchangeinformant.feed.dto.MessageInDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Component
public class StreamListener {

    private final MessageService messageService;

    public StreamListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @Bean
    public Consumer<MessageInDTO> rabbitListener() {
        return (messageInDTO) -> {
            log.info("Message \" {} \" received by listener", messageInDTO);
            messageService.receiveMessage(messageInDTO);
        };
    }

    @Bean
    public Supplier<MessageInDTO> testPutController() {
        return () -> MessageController.sendMessage();
    }

}

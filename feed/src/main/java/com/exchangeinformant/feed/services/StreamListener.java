package com.exchangeinformant.feed.services;


import com.exchangeinformant.feed.dto.MessageInDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * RabbitMQ Spring Cloud
 */
@Slf4j
@Component
public class StreamListener {
    private final MessageService messageService;

    @Autowired
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
}

package com.example.notifier.processor;

import com.example.notifier.function.TriFunction;
import com.example.notifier.model.IncomingMessage;
import com.example.notifier.model.Message;
import com.example.notifier.model.User;
import com.example.notifier.service.TemplateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@AllArgsConstructor
public class MessageManager implements TriFunction<Message, String, IncomingMessage> {
    private final ObjectMapper objectMapper;
    private final TemplateService templateService;

    @Override
    public Message apply(String responseFromUserProfile, IncomingMessage incomingMessage) {
        MessageBuilder messageBuilder = MessageBuilder.builder()
                .template(templateService.getTemplateById(incomingMessage.getTemplateId()))
                .serviceSender(incomingMessage.getServiceSender())
                .subject(incomingMessage.getSubject())
                .user(mapResponseFromUserProfile(responseFromUserProfile))
                .texts(incomingMessage.getTexts())
                .build();
        return messageBuilder.newMessage();
    }

    private User mapResponseFromUserProfile(String json) {
        User user;
        try {
            user = objectMapper.readValue(json, User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}


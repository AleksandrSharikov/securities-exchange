package com.example.notifier.processor;

import com.example.notifier.function.ThreeFunction;
import com.example.notifier.model.Message;
import com.example.notifier.model.User;
import com.example.notifier.util.MessageBuilder;
import com.example.notifier.service.TemplateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@AllArgsConstructor
public class MessageManager implements ThreeFunction<Message, String, String> {
    private final ObjectMapper objectMapper;
    private final TemplateService templateService;

    @Override
    public Message apply(String data1, String data2) {
        Map<String, Object> data = getDataFromJson(data2);
        MessageBuilder messageBuilder = MessageBuilder.builder()
                .template(templateService.getTemplateById(Long.parseLong(data.get("templateId").toString())))
                .serviceSender((String) data.get("serviceSend"))
                .subject((String) data.get("subject"))
                .user(getUserFromJson(data1))
                .data(data)
                .build();
        System.out.println();
        return messageBuilder.newMessage();
    }

    private User getUserFromJson(String json) {
        User user = null;
        try {
            user = objectMapper.readValue(json, User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    private Map<String, Object> getDataFromJson(String json) {
        Map<String, Object> data = null;
        try {
            data = objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}

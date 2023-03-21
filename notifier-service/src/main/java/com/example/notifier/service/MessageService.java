package com.example.notifier.service;

import com.example.notifier.sender.EmailSender;
import com.example.notifier.util.MapperJson;
import com.example.notifier.util.MessageBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class MessageService {
    private final TemplateService templateService;
    private final MapperJson mapperJson;
    private final EmailSender emailSender;

    public MessageService(TemplateService templateService, MapperJson mapperJson, EmailSender emailSender) {
        this.templateService = templateService;
        this.mapperJson = mapperJson;
        this.emailSender = emailSender;
    }

    public void messageManager(String jsonMessage) {
        Map<String, Object> json = mapperJson.mapperFromJsonToHashMap(jsonMessage);

        MessageBuilder messageBuilder = MessageBuilder.builder()
                .template(templateService.getTemplateById((int) json.get("id_template")))
                .data(json)
                .build();

        String newMessage = messageBuilder.newMessage();

        log.info("Message: {}", newMessage);

        emailSender.sendMessage("westalex777@yandex.ru", "Test", newMessage);
    }
}

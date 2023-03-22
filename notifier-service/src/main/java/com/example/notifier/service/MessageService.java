package com.example.notifier.service;

import com.example.notifier.processor.UserInfoProcessor;
import com.example.notifier.sender.EmailSender;
import com.example.notifier.util.MapperJson;
import com.example.notifier.util.MessageBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageService {
    private final TemplateService templateService;
    private final MapperJson mapperJson;
    private final EmailSender emailSender;
    private final UserInfoProcessor userInfoProcessor; //TODO перенести, здесь только для теста

    public MessageService(TemplateService templateService, MapperJson mapperJson, EmailSender emailSender, UserInfoProcessor userInfoProcessor) {
        this.templateService = templateService;
        this.mapperJson = mapperJson;
        this.emailSender = emailSender;
        this.userInfoProcessor = userInfoProcessor;
    }

    /**
     *
     * Метод отправки сообщения пользователя, по установленным данным и выбранному способу отправки
     */
    private void sendMessage(MessageBuilder messageBuilder) {
//        emailSender.sendMessage(to, subject, textMessage);
        log.info("Message to {} sent", messageBuilder.getRecipient());
    }

    /**
     *
     * Метод вызывает MessageBuilder для создания сообщения. Затем вызывает метод sendMessage().
     */
    public void messageManager(String jsonMessage) {
        MessageBuilder messageBuilder = MessageBuilder.builder()
                .template(templateService.getTemplateById(mapperJson.getTemplateId(jsonMessage)))
                .serviceSender(mapperJson.getServiceSender(jsonMessage))
                .subject(mapperJson.getSubjectMessage(jsonMessage))
                .recipient(userInfoProcessor.getUserEmail(mapperJson.getUserId(jsonMessage)))
                .data(mapperJson.mapperFromJsonToHashMap(jsonMessage))
                .build();
        sendMessage(messageBuilder);
    }
}

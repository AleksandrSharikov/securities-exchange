package com.example.notifier.service;

import com.example.notifier.kafka.KafkaProducer;
import com.example.notifier.model.Message;
import com.example.notifier.model.User;
import com.example.notifier.sender.EmailSender;
import com.example.notifier.util.MapperJson;
import com.example.notifier.util.MessageBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class MessageService {
    private final TemplateService templateService;
    private final MapperJson mapper;
    private final EmailSender emailSender;
    private final Map<Long, LinkedList<String>> storageInMessageFromOtherServices;
    private final KafkaProducer kafkaProducer;
    private final LinkedList<User> storageUserInfo;

    public void inMessageProcessor(String inMessage) {

//        Задача для первого подписчика складывает все входящие в хранилище №1
//        long userId = mapper.getUserId(inMessage);
//        storageInMessageFromOtherServices.computeIfAbsent(userId, key -> new LinkedList<>()).add(inMessage);


//        Задача для второго подписчика, который берет из хранилища №1 и отправляет запрос на user - profile
//        if (!storageInMessageFromOtherServices.isEmpty()) {
//            long userId = storageInMessageFromOtherServices.keySet().iterator().next();
//            String requestToUserProfile = mapper.createJson("userId", userId);
//            kafkaProducer.sendMessage("request-to-userProfile-from-notifier", requestToUserProfile);
//        }
    }

    private void sendMessage(MessageBuilder messageBuilder) {
//        emailSender.sendMessage(to, subject, textMessage);
//        log.info("Message to {} sent", messageBuilder.getRecipient());
    }

    public LinkedList<Message> messageManager(@NotNull User user) {
        LinkedList<String> data = storageInMessageFromOtherServices.get(user.getId());
        LinkedList<Message> messages = new LinkedList<>();
        for (String json : data) {
            MessageBuilder messageBuilder = MessageBuilder.builder()
                    .template(templateService.getTemplateById(mapper.getTemplateId(json)))
                    .serviceSender(mapper.getServiceSender(json))
                    .subject(mapper.getSubjectMessage(json))
                    .user(user)
                    .data(mapper.mapperFromJsonToHashMap(json))
                    .build();
            messages.add(messageBuilder.newMessage());
        }
        return messages;
    }
}

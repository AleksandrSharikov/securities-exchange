package com.example.notifier.processor;

import com.example.notifier.util.MapperJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.function.Supplier;

/**
 * Processor который подписан на входящие от других сервисов.
 * По получению данных отправляет id юзера в сервис User-Profile
 */
@Component
@Slf4j
public class RequestToUserProfileProcessor implements Supplier<Message<String>> {
    private final LinkedList<String> storageInMessageFromOtherServices;
    private final MapperJson mapperJson;

    public RequestToUserProfileProcessor(
            @Qualifier("storage-inMessageFromOtherServices") LinkedList<String> storageInMessageFromOtherServices,
            MapperJson mapperJson) {
        this.storageInMessageFromOtherServices = storageInMessageFromOtherServices;
        this.mapperJson = mapperJson;
    }

    @Override
    public Message<String> get() {
        if (!storageInMessageFromOtherServices.isEmpty()) {
            String requestJson = mapperJson.createJson("user_id",
                    mapperJson.getUserId(storageInMessageFromOtherServices.peek()));
            log.info("Request to User Profile -> user_id = {}", requestJson);
            return MessageBuilder.withPayload(requestJson).build();
        }
        return null;
    }
}

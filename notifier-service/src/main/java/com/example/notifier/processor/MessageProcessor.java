package com.example.notifier.processor;

import com.example.notifier.mapper.ModelMapperJson;
import com.example.notifier.model.OrderMessage;
import com.example.notifier.model.User;
import com.example.notifier.sender.SenderService;
import com.example.notifier.service.CleanerService;
import com.example.notifier.service.MessageBuilder;
import com.example.notifier.service.TemplateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;
import java.util.function.BiConsumer;

@Component
@Slf4j
@AllArgsConstructor
public class MessageProcessor implements BiConsumer<String, String> {
    private final Map<String, OrderMessage> storageInMessageFromOtherServices;
    private final SenderService senderService;
    private final TemplateService templateService;
    private final CleanerService cleanerService;

    @Override
    public void accept(String key, String value) {
        OrderMessage orderMessage = storageInMessageFromOtherServices.get(key);
        Mono.just(ModelMapperJson.readFromJson(value, User.class))
                .publishOn(Schedulers.boundedElastic())
                .map(user ->
                        MessageBuilder.builder()
                                .orderMessage(orderMessage)
                                .template(templateService.getTemplateById(orderMessage.getTemplateId()))
                                .user(user)
                                .build())
                .map(MessageBuilder::newMessage)
                .doOnNext(senderService)
                .doOnError(error -> log.error(error.getMessage()))
                .doFinally(x -> cleanerService.accept(key))
                .subscribe();
    }
}

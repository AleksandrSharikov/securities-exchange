package com.example.notifier.processor;

import com.example.notifier.kafka.KafkaProducerNotifier;
import com.example.notifier.mapper.ModelMapperJson;
import com.example.notifier.model.OrderMessage;
import com.example.notifier.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

@Component
@Slf4j
@AllArgsConstructor
public class OrderMessageProcessor implements Consumer<String> {
    private final Map<String, OrderMessage> storageInMessageFromOtherServices;
    private final KafkaProducerNotifier kafkaProducerNotifier;

    @Override
    public void accept(String message) {
        String key = generationKey();
        Mono.just(ModelMapperJson.readFromJson(message, OrderMessage.class))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(orderMessage -> storageInMessageFromOtherServices.put(key, orderMessage))
                .doOnNext(orderMessage -> requestToUserProfile(User.builder().id(orderMessage.getUserId()).build(), key))
                .doOnError(error -> log.error(error.getMessage()))
                .subscribe();
    }

    private void requestToUserProfile(User user, String key) {
        kafkaProducerNotifier.sendMessage("request-to-userProfile-from-notifier", key, ModelMapperJson.writeToJson(user));
        log.info("outgoing to user-profile -> key: {}, value: {}", key, user);
    }

    private String generationKey() {
        Random random = new Random();
        return String.valueOf(random.nextInt());
    }
}

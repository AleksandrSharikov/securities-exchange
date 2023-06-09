package com.example.notifier.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    /**
     * Топик для Apache Kafka
     * @return приходят сообщения от других сервисов с заказом отправки сообщения пользователю
     */
    @Bean
    public NewTopic toNotifier() {
        return TopicBuilder.name("to-notifier")
                .partitions(10)
                .replicas(1)
                .build();
    }

    /**
     * Топик для Apache Kafka
     * @return Notifier отправляет запрос на данные пользователя в User-Profile
     */
    @Bean
    public NewTopic requestToUserProfileFromNotifier() {
        return TopicBuilder.name("request-to-userProfile-from-notifier")
                .partitions(10)
                .replicas(1)
                .build();
    }

    /**
     * Топик для Apache Kafka
     * @return Notifier получает ответ на запрос на данные пользователя от User-Profile
     */
    @Bean
    public NewTopic responseUserProfileToNotifier() {
        return TopicBuilder.name("response-userProfile-to-notifier")
                .partitions(10)
                .replicas(1)
                .build();
    }
}
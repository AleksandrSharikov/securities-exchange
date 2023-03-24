package com.example.notifier.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic toNotifier() {
        return TopicBuilder.name("to-notifier")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic requestToUserProfileFromNotifier() {
        return TopicBuilder.name("request-to-userProfile-from-notifier")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic responseUserProfileToNotifier() {
        return TopicBuilder.name("response-userProfile-to-notifier")
                .partitions(10)
                .replicas(1)
                .build();
    }
}

//TODO точнее определить списки топиков и дать им корректные имена, очистить кафку от других топиков
package com.example.kafka1;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Kafka1Application {

    public static void main(String[] args) {
        SpringApplication.run(Kafka1Application.class, args);
    }

//    @Bean
//    public NewTopic topic() {
//        return TopicBuilder.name("topic1")
//                .partitions(10)
//                .replicas(1)
//                .build();
//    }
//
//    @Bean
//    public ApplicationRunner runner(KafkaTemplate<String, String> template) {
//        System.out.println("Message sent!");
//        return args -> {
//            template.send("topic1", "test");
//        };
//    }
//
//    @Bean
//    public NewTopic topic1() {
//        return TopicBuilder.name("topic2")
//                .partitions(10)
//                .replicas(1)
//                .build();
//    }
//
//    @Bean
//    public ApplicationRunner runner2(KafkaTemplate<String, String> template) {
//        System.out.println("Message sent!");
//        return args -> {
//            template.send("topic2", "test2");
//        };
//    }
}

package com.example.notifiertest.service;

import com.example.notifiertest.processor.KafkaProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
@AllArgsConstructor
@Slf4j
public class LinkedListJsonChangeListener {
    private final KafkaProducer kafkaProducer;

    @EventListener
    @ConditionalOnBean(name = "json-event-source")
    public void onListChange(LinkedList<String> linkedListJson) {
        kafkaProducer.sendMessage(linkedListJson.peek());
        log.info("outgoing message: {}", linkedListJson.poll());
    }
}
//package com.example.notifiertest.processor;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//
//import java.util.LinkedList;
//import java.util.function.Supplier;
//
//@Component
//@Slf4j
//public class JsonEventProcessor implements Supplier<Message<String>> {
//    @Qualifier("json-event-source")
//    private final LinkedList<String> jsonCreationEventSource;
//
//    public JsonEventProcessor(LinkedList<String> jsonCreationEventSource) {
//        this.jsonCreationEventSource = jsonCreationEventSource;
//    }
//
//    @Override
//    public Message<String> get() {
//        if (jsonCreationEventSource.peek() != null) {
//            log.info("sending new json: {}", jsonCreationEventSource.peek());
//            return MessageBuilder.withPayload(jsonCreationEventSource.poll())
//                    .build();
//        }
//        return null;
//    }
//}

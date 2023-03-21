package com.example.notifiertest.config;

import com.example.notifiertest.model.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;

@Configuration
public class ConfigEventSource {

    @Bean("book-event-source")
    public LinkedList<Book> createBookCreationEventSource() {
        return new LinkedList<>();
    }

    @Bean("json-event-source")
    public LinkedList<String> createLinkedListJSON() {
        return new LinkedList<>();
    }
}

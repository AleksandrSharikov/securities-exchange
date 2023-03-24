package com.example.notifiertest.config;

import com.example.notifiertest.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ConfigEventSource {

    @Bean("json-event-source")
    public LinkedList<String> createLinkedListJSON() {
        return new LinkedList<>();
    }

    @Bean("Users-for-test")
    public List<User> createListUsers() {
        return new ArrayList<>(Arrays.asList(
                new User(1, "email_1_@email.ru", "+7 777 777 77 01"),
                new User(2, "email_2_@email.ru", "+7 777 777 77 02"),
                new User(3, "email_3_@email.ru", "+7 777 777 77 03"),
                new User(4, "email_4_@email.ru", "+7 777 777 77 04"),
                new User(5, "email_5_@email.ru", "+7 777 777 77 05")));
    }
}

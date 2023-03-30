package com.example.notifier.service;

import com.example.notifier.model.OrderMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Данный класс очищает бин - "storage-inMessageFromOtherServices", после того как данные уже использованы.
 * Это делается с целью недопущения утечки памяти.
 */
@Service
@AllArgsConstructor
@Slf4j
public class CleanerService implements Consumer<String> {
    private final Map<String, OrderMessage> storageInMessageFromOtherServices;

    @Override
    public void accept(String s) {
        storageInMessageFromOtherServices.remove(s);
        log.info("Delete from bean storageInMessageFromOtherServices, object with key: {}", s);
    }
}

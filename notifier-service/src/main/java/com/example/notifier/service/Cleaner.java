package com.example.notifier.service;

import com.example.notifier.model.IncomingMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class Cleaner {
    private final Map<String, IncomingMessage> storageInMessageFromOtherServices;

    public void cleaningStorage(String key) {
        storageInMessageFromOtherServices.remove(key);
    }
}

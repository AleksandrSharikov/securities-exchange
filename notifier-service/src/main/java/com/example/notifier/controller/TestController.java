package com.example.notifier.controller;

import com.example.notifier.model.Message;
import com.example.notifier.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final MessageService messageService;

    public TestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/api")
    public ResponseEntity<Message> test() {
        messageService.saveMessage(new Message("title-", "text-", "platform-"));
        return ResponseEntity.ok().build();
    }
}

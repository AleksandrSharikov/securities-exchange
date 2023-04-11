package com.example.notifier.service;

import com.example.notifier.model.Message;

import java.util.LinkedList;
import java.util.Optional;

public interface MessageService {

    void save(Message message);
    Optional<LinkedList<Message>> getMessagesByNotSend();
}

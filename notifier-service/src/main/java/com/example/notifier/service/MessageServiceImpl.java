package com.example.notifier.service;

import com.example.notifier.model.Message;
import com.example.notifier.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }
}

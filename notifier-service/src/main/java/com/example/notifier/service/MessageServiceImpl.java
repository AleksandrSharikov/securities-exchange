package com.example.notifier.service;

import com.example.notifier.enums.SendingStatus;
import com.example.notifier.model.Message;
import com.example.notifier.repository.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class MessageServiceImpl implements MessageService{
    private MessageRepository messageRepository;

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    /**
     *
     * @return Все сообщения из БД со статусом NOT_SENT
     */
    @Override
    public Optional<LinkedList<Message>> getMessagesByNotSend() {
        return messageRepository.getAllBySendingStatus(SendingStatus.NOT_SENT);
    }

}

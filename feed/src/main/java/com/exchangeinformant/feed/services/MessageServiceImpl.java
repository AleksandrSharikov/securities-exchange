package com.exchangeinformant.feed.services;


import com.exchangeinformant.feed.model.Message;
import com.exchangeinformant.feed.model.Rank;
import com.exchangeinformant.feed.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;


  //  MessageServiceImpl(MessageRepository messageRepository){        this.messageRepository = messageRepository;    };
    @Override
    public void receiveMessage(Message message) {
        message.setUnread(true);
        messageRepository.save(message);
    }


    public List<Message> unreadMessageList(long userId){
       return unreadMessageList(userId, Rank.LOW);
    }
    @Override
    public List<Message> unreadMessageList(long userId, Rank rank) {
        return null;
    }
}

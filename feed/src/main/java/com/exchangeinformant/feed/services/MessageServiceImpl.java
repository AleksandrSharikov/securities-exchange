package com.exchangeinformant.feed.services;


import com.exchangeinformant.feed.DTO.MessageDTO;
import com.exchangeinformant.feed.model.Message;
import com.exchangeinformant.feed.model.Rank;
import com.exchangeinformant.feed.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;


  //  MessageServiceImpl(MessageRepository messageRepository){        this.messageRepository = messageRepository;    };
    @Override
    public void receiveMessage(MessageDTO messageDTO) {
        log.info("Message \" {} \" go to service", messageDTO);
        Message message = new Message(messageDTO);
       // message.setReceivingTime(LocalDateTime.now());
        message.setUnread(true);
       // message.setRank(Rank.MEDIUM);
        message.setId(1L);
        log.info("MessageDTO translated to message  \" {} \" and prepared to save", message);
        messageRepository.save(message);
        log.info("Message has been processed");
    }


    public List<Message> unreadMessageList(long userId){
       return unreadMessageList(userId, Rank.LOW);
    }
    @Override
    public List<Message> unreadMessageList(long userId, Rank rank) {
    return null;//messageRepository.messagesForUser(userId);
    }
}

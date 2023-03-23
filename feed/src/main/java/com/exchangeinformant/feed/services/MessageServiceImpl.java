package com.exchangeinformant.feed.services;


import com.exchangeinformant.feed.DTO.MessageDTO;
import com.exchangeinformant.feed.model.Message;
import com.exchangeinformant.feed.repository.MessageRepository;
import com.exchangeinformant.feed.repository.SourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final SourceRepository sourceRepository;

    @Autowired
    MessageServiceImpl(MessageRepository messageRepository, SourceRepository sourceRepository){
        this.messageRepository = messageRepository;
        this.sourceRepository = sourceRepository;
    }
    @Override
    public void receiveMessage(MessageDTO messageDTO) {
        log.info("Message \" {} \" go to service", messageDTO);
        Message message = new Message(messageDTO);
        message.setReceivingTime(LocalDateTime.now());
        message.setUnread(true);
        setRank(message);
        log.info("MessageDTO translated to message  \" {} \" and prepared to save", message);
        messageRepository.save(message);
        log.info("Message has been processed");
    }

    void setRank(Message message) {
        message.setRank(sourceRepository.getReferenceById(message.getSourceId()).getDefaultRank());

    }


    public List<Message> unreadMessageList(long userId){
       return unreadMessageList(userId, 0);
    }


    @Override
    public List<Message> unreadMessageList(long userId, int rank) {
        List<Message> answer = messageRepository.messagesForUser(userId, rank);
        messageRepository.markListRead(userId, rank);
    return answer;

    }
}

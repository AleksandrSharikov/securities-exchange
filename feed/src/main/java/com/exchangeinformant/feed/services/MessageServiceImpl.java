package com.exchangeinformant.feed.services;


import com.exchangeinformant.feed.dto.MessageInDTO;
import com.exchangeinformant.feed.dto.MessageOutDTO;
import com.exchangeinformant.feed.mappers.MessageMapperImpl;
import com.exchangeinformant.feed.model.Message;
import com.exchangeinformant.feed.repository.MessageRepository;
import com.exchangeinformant.feed.repository.SourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final SourceRepository sourceRepository;
    private final MessageMapperImpl messageMapper;

    @Autowired
    MessageServiceImpl(MessageRepository messageRepository,
                       SourceRepository sourceRepository,
                       MessageMapperImpl messageMapper){
        this.messageRepository = messageRepository;
        this.sourceRepository = sourceRepository;
        this.messageMapper = messageMapper;
    }
    @Override
    public void receiveMessage(MessageInDTO messageInDTO) {
        log.info("Message \" {} \" go to service", messageInDTO);
        Message message = new Message(messageInDTO);
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


    public List<MessageOutDTO> unreadMessageList(long userId){
       return unreadMessageList(userId, 0);
    }


    @Override
    public List<MessageOutDTO> unreadMessageList(long userId, int rank) {
        List<Message> messages = messageRepository.messagesForUser(userId, rank);
        List<MessageOutDTO> answer = new ArrayList<>();
        for(Message message : messages) {
            answer.add(messageMapper.messageToTdo(message));
        }

        messageRepository.markListRead(userId, rank);
    return answer;

    }
}

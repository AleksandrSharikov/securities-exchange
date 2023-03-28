package com.exchangeinformant.feed.mappers;

// применение шаблона к одтаваемому сообщению

import com.exchangeinformant.feed.dto.MessageOutDTO;
import com.exchangeinformant.feed.model.Message;
import com.exchangeinformant.feed.repository.PatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageMapperImpl {
    private final PatternRepository patternRepository;
    @Autowired
    public MessageMapperImpl(PatternRepository patternRepository) {
        this.patternRepository = patternRepository;
    }

    public MessageOutDTO  messageToTdo(Message  message) {

        if(patternRepository.existsById(message.getType_id())) {
            String[] datas = message.getData().split(",");
            return new MessageOutDTO(String.format(patternRepository.getReferenceById(message.getType_id()).getPattern(), datas));
        }

        return new MessageOutDTO(message.getData());
    }

}

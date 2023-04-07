package com.exchangeinformant.feed.mappers;


import com.exchangeinformant.feed.dto.MessagePreDTO;
import com.exchangeinformant.feed.model.Message;
import com.exchangeinformant.feed.repository.PatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.MissingFormatArgumentException;

/**
 * Mapper of a message from DB to DTO sent to the front end
 */
@Service
public class MessagePreDTOMap {
    private final PatternRepository patternRepository;
    @Autowired
    public MessagePreDTOMap(PatternRepository patternRepository) {
        this.patternRepository = patternRepository;
    }

    //Если для сообщения предусмотрен шаблон, пытаемся его применить

    /**
     * Mapper
     * @param message Message from DB
     * @return DTO to be sent to the front end
     */
    public MessagePreDTO messageToPreTDO(Message  message) {
        MessagePreDTO messagePreDTO = new MessagePreDTO();
        messagePreDTO.setMessageID(message.getId());
        messagePreDTO.setUnread(message.isUnread());

        if (patternRepository.existsById(message.getType_id())) {
            try {
                String[] datas = message.getData().split(",");
                messagePreDTO.setMessage(String.format(patternRepository.getReferenceById(message.getType_id()).getPattern(), datas));
                // В случае ошибки шаблона всё равно отдаём сообщение на фронтэнд, но с замечанием
                // В принципе можно и статус ответа изменить, если нужно
            } catch (MissingFormatArgumentException mfae) {
                messagePreDTO.setMessage(message.getData() + " <- WRONG FORMAT!");
            }
        } else {
            messagePreDTO.setMessage(message.getData());
        }
        return messagePreDTO;
    }

}

package com.exchangeinformant.feed.mappers;

import com.exchangeinformant.feed.dto.MessageOutDTO;
import com.exchangeinformant.feed.model.Message;
import com.exchangeinformant.feed.repository.PatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.MissingFormatArgumentException;

/**
 * Mapper of a message from DB to DTO sent to the front end
 */
@Service
public class MessageMapperImpl {
    private final PatternRepository patternRepository;

    @Autowired
    public MessageMapperImpl(PatternRepository patternRepository) {
        this.patternRepository = patternRepository;
    }

    //Если для сообщения предусмотрен шаблон, пытаемся его применить

    /**
     * Mapper
     *
     * @param message Message from DB
     * @return DTO to be sent to the front end
     */
    public MessageOutDTO messageToTdo(Message message) {

        if (patternRepository.existsById(message.getType_id())) {
            try {
                String[] datas = message.getData().split(",");
                return new MessageOutDTO(String.format(patternRepository.getReferenceById(message.getType_id()).getPattern(), datas));
                // В случае ошибки шаблона всё равно отдаём сообщение на фронтэнд, но с замечанием
                // В принципе можно и статус ответа изменить, если нужно
            } catch (MissingFormatArgumentException mfae) {
                return new MessageOutDTO(message.getData() + " <- WRONG FORMAT!");
            }
        }

        // Если шаблона нет, возвращаем только данные
        return new MessageOutDTO(message.getData());
    }

}

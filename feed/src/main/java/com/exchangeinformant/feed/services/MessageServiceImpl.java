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

/**
 * Сервис для сообщений
 */
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

    /**
     * Получение и сохранение сообщения в БД
     * @param messageInDTO Непосредственно, входящее сообщение из очереди
     */
    @Override
    public void receiveMessage(MessageInDTO messageInDTO) {
        log.info("Message \" {} \" go to service", messageInDTO);
        Message message = new Message(messageInDTO);      // Создание объекта БД из ДТО в конструкторе
        message.setReceivingTime(LocalDateTime.now());    // Запись времени получения
        setRank(message);                                 // Попытка определить важность по источнику
        log.info("MessageDTO translated to message  \" {} \" and prepared to save", message);
        messageRepository.save(message);                  // Сохранение в БД
        log.info("Message has been processed");
    }

    /**
     * Попытка определить важность по источнику
     * @param message Сообщение
     */
    void setRank(Message message) {
        if(sourceRepository.existsById(message.getSourceId())) {
            message.setRank(sourceRepository.getReferenceById(message.getSourceId()).getDefaultRank());
        }

    }

    /**
     * Перегруженный следующий метод для получения сообщения со всеми важностями
     * @param userId ID пользователя, для которого запрашивается сообщение
     * @return Страница списка сообщений для отправки на фронтенд
     */
    public List<MessageOutDTO> unreadMessageList(long userId){
       return unreadMessageList(userId, 0);
    }

    /**
     * Получения списка сообщений
     * @param userId ID пользователя, для которого запрашивается сообщение
     * @param rank Минимальная важность сообщения
     * @return Страница списка сообщений для отправки на фронтенд
     */
    @Override
    public List<MessageOutDTO> unreadMessageList(long userId, int rank) {
        // Получение списка сообщений из БД
        List<Message> messages = messageRepository.messagesForUser(userId, rank, MessageRepository.pageable);
        List<MessageOutDTO> answer = new ArrayList<>();
        for(Message message : messages) {
            answer.add(messageMapper.messageToTdo(message)); // Мэппинг каждого сообщения из БД в ДТО
            messageRepository.markRead(message.getId());     // Пометка каждого сообщения прочиитанным
        }
        return answer;
    }
}

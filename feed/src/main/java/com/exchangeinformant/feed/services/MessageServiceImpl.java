package com.exchangeinformant.feed.services;


import com.exchangeinformant.feed.dto.MessageInDTO;
import com.exchangeinformant.feed.dto.MessageOutDTO;
import com.exchangeinformant.feed.dto.MessagePreDTO;
import com.exchangeinformant.feed.mappers.MessageMapperImpl;
import com.exchangeinformant.feed.mappers.MessagePreDTOMap;
import com.exchangeinformant.feed.model.Message;
import com.exchangeinformant.feed.repository.MessageRepository;
import com.exchangeinformant.feed.repository.SourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Messages service
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final SourceRepository sourceRepository;
    private final MessageMapperImpl messageMapper;
    private final MessagePreDTOMap messagePreDTOMap;

    @Autowired
    MessageServiceImpl(MessageRepository messageRepository,
                       SourceRepository sourceRepository,
                       MessageMapperImpl messageMapper,
                        MessagePreDTOMap messagePreDTOmap){
        this.messageRepository = messageRepository;
        this.sourceRepository = sourceRepository;
        this.messageMapper = messageMapper;
        this.messagePreDTOMap = messagePreDTOmap;
    }

    /**
     * Receiving and saving messages in DB
     * @param messageInDTO The incoming message from RabbitMQ
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
     * Attempt to find sources rank
     * @param message The message
     */
    void setRank(Message message) {
        if(sourceRepository.existsById(message.getSourceId())) {
            message.setRank(sourceRepository.getReferenceById(message.getSourceId()).getDefaultRank());
        }

    }

    /**
     * The overloaded next method for getting messages with all ranks
     * @param userId ID of the user fot whom messages are required
     * @return a page of the message list to be sent to the front end
     */
    @Override
    public List<MessageOutDTO> unreadMessageList(long userId){
       return unreadMessageList(userId, 0);
    }

    /**
     * Getting the read messages list
     * @param userId ID of the user fot whom messages are required
     * @param rank Minimum rank of the message
     * @return a page of the message list to be sent to the front end
     */
    @Override
    public List<MessageOutDTO> unreadMessageList(long userId, int rank) {
        // Получение списка сообщений из БД
        Pageable pageable = PageRequest.of(0, 5, Sort.by("receivingTime").descending());

        List<Message> messages = messageRepository.messagesForUser(userId, rank, pageable);
        List<MessageOutDTO> answer = new ArrayList<>();
        for(Message message : messages) {
            answer.add(messageMapper.messageToOutDTO(message)); // Мэппинг каждого сообщения из БД в ДТО
            messageRepository.markRead(message.getId());     // Пометка каждого сообщения прочиитанным
        }
        return answer;
    }

    /**
     * Overloaded method
     * @param userId ID of the user fot whom messages are required
     * @param page number of required page
     * @return a page of the message list to be sent to the front end
     */
    @Override
    public List<MessagePreDTO> allMessageList(long userId, int page){
        return allMessageList(userId, 0, page);
    }

    /**
     * Getting the messages list
     * @param userId ID of the user fot whom messages are required
     * @param rank Minimum rank of the message
     * @param page number of required page
     * @return a page of the message list to be sent to the front end
     */
    @Override
    public List<MessagePreDTO> allMessageList(long userId, int rank, int page) {
        // Получение списка сообщений из БД
        Pageable pageable = PageRequest.of(page, 5, Sort.by("receivingTime").descending());
        List<Message> messages = messageRepository.messagesAllForUser(userId, rank, pageable);
        List<MessagePreDTO> answer = new ArrayList<>();
        for(Message message : messages) {
            answer.add(messagePreDTOMap.messageToPreTDO(message)); // Мэппинг каждого сообщения из БД в ДТО
        }
        return answer;
    }

    @Override
    public MessageOutDTO getFullMessage(long messageId) {
        messageRepository.markRead(messageId);
        return messageMapper.messageToOutDTO(messageRepository.getReferenceById(messageId));
    }

}

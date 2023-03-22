package com.exchangeinformant.feed.config;

import com.exchangeinformant.feed.model.Message;
import com.exchangeinformant.feed.repository.MessageRepository;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SaveMessageTest implements ApplicationRunner {

    private MessageRepository messageRepository;
    @Autowired
    public SaveMessageTest(MessageRepository messageRepository) {this.messageRepository = messageRepository;}
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Appruner starts");
        Message message = new Message(null,5L, true, LocalDateTime.now(),1, "mes",2,"data");
        messageRepository.save(message);

        message = new Message(null,5L, true, LocalDateTime.now(),1, "mes",2,"data2");
        messageRepository.save(message);

        message = new Message(null,5L, true, LocalDateTime.now(),1, "mes",2,"data3");
        messageRepository.save(message);

        message = new Message(null,5L, true, LocalDateTime.now(),1, "mes",2,"data4");
        messageRepository.save(message);

        message = new Message(null,5L, false, LocalDateTime.now(),1, "mes",2,"readed");
        messageRepository.save(message);


        message = new Message(null,5L, true, LocalDateTime.now(),1, "mes",1,"rank1");
        messageRepository.save(message);


        message = new Message(null,5L, true, LocalDateTime.now(),1, "mes",3,"rank3");
        messageRepository.save(message);


        message = new Message(null,5L, true, LocalDateTime.now(),1, "mes",2,"last one");

        messageRepository.save(message);


        System.out.println("Appruner done");


    }
}

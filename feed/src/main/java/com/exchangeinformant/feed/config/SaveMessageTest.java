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
        Message message = new Message(1L,1L,true, LocalDateTime.now(),1, "mes",2,"data");
        messageRepository.save(message);
        System.out.println("Appruner done");


    }
}

package com.exchangeinformant.feed.config;


// Тестовый класс для заполнения БД при старте в целях тестирования


import com.exchangeinformant.feed.model.Message;
import com.exchangeinformant.feed.model.Patterns;
import com.exchangeinformant.feed.repository.MessageRepository;
import com.exchangeinformant.feed.repository.PatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SaveMessageTest implements ApplicationRunner {

    private final MessageRepository messageRepository;
    private final PatternRepository patternRepository;
    @Autowired
    public SaveMessageTest(MessageRepository messageRepository,
                           PatternRepository patternRepository) {
        this.messageRepository = messageRepository;
        this.patternRepository = patternRepository;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Appruner starts");
        Patterns patternTest =  new Patterns(
                1, "Test pattern", "Первая информация: %1$s, вторая: %2$s, а  всего их: %3$s");
        patternRepository.save(patternTest);


        Message message = new Message(
                null,5L, true, LocalDateTime.now(),1, 1,2,"data,test,5");
        messageRepository.save(message);

        message = new Message(
                null,5L, true, LocalDateTime.now(),1, 1,2,"data2,test2,7");
        messageRepository.save(message);

        message = new Message(
                null,5L, true, LocalDateTime.now(),1, 2,2,"data3,other type,7");
        messageRepository.save(message);

        message = new Message(
                null,5L, true, LocalDateTime.now(),1, 1,2,"data4,test4,7");
        messageRepository.save(message);

        message = new Message(
                null,5L, false, LocalDateTime.now(),1, 1,2,"data5,test2,7");
        messageRepository.save(message);


        message = new Message(
                null,5L, true, LocalDateTime.now(),1, 1,1,"data6,rank1,7");
        messageRepository.save(message);


        message = new Message(
                null,5L, true, LocalDateTime.now(),1, 1,3,"data7,rank3,7");
        messageRepository.save(message);


        message = new Message(
                null,5L, true, LocalDateTime.now(),1, 3,2,"last info");

        messageRepository.save(message);


        System.out.println("Appruner done");


    }
}

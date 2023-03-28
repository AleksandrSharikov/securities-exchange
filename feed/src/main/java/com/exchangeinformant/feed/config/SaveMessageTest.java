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
            Patterns patternTest =  new Patterns(
                1, "Test pattern", "Первая информация: %1$s, вторая: %2$s");
        patternRepository.save(patternTest);

        Message message = new Message(
                null,5L, true, LocalDateTime.now(),1, 1,3,"data7fsd");
        messageRepository.save(message);


    }
}

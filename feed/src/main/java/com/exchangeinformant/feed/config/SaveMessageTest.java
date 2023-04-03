package com.exchangeinformant.feed.config;

import com.exchangeinformant.feed.model.Message;
import com.exchangeinformant.feed.model.Patterns;
import com.exchangeinformant.feed.repository.MessageRepository;
import com.exchangeinformant.feed.repository.PatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Тестовый класс для заполнения БД при старте в целях тестирования
 */

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
    //Класс оставлен для тестирования. В конечной версии нужно удалить

    }
}

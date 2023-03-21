package com.example.notifiertest.service;

import com.example.notifiertest.model.Book;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Random;

@Component
@AllArgsConstructor
@Slf4j
public class BookService {
    private final LinkedList<Book> bookCreationEvents;

    @Scheduled(fixedRate = 5000)
    public void generateBook() {
        Book book = creatBook();
//        log.info("creat book: " + book);
        bookCreationEvents.add(book);
    }

    private Book creatBook() {
        Random random = new Random();
        long id = random.nextLong(1_000_000_000);
        String name = "Random_name_" + id;
        return new Book(id, name);
    }
}

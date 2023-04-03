package com.exchangeinformant.feed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Karpov Aleksey, Sharikov Aleksandr
 * The service of receiving messages from different other services, storing them and sending to the users news feed
 */
@SpringBootApplication
public class FeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedApplication.class, args);
    }

}

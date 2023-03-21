package com.example.notifiertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotifierTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifierTestApplication.class, args);
	}

}

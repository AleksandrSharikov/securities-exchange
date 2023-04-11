package com.example.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс Spring Boot-приложения "core".
 * Он использует аннотацию @SpringBootApplication для указания, что это главный класс и
 * автоматически сканирует и загружает все компоненты (классы с аннотациями @Component,
 * @Service, @Repository, @Controller и т.д.) внутри пакета и его подпакетов.
 * Метод main() запускает приложение Spring Boot.
 *
 * @see SpringBootApplication
 */
@SpringBootApplication
public class CoreApplication {

    /**
     * Запуск Spring Boot-приложения.
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

}

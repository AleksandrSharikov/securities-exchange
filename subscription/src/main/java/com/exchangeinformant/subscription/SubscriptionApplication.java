package com.exchangeinformant.subscription;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Основной класс Spring приложения для управления подписками.
 * Используется аннотация @SpringBootApplication, которая является сокращением для использования аннотаций
 * @Configuration, @EnableAutoConfiguration и @ComponentScan со стандартными значениями.
 *
 * Аннотация @EnableDiscoveryClient указывает на то, что приложение будет использовать возможности обнаружения
 * сервисов Spring Cloud.
 *
 * Аннотация @OpenAPIDefinition указывает на наличие OpenAPI документации для приложения.
 *
 * Настройки OpenAPI документации указываются в параметрах аннотации, таких как название, версия и описание API,
 * а также URL-адрес сервера.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Subscription API",
                version = "beta",
                description = "API для CRUD операций с подписками."
        ),
        servers = @Server(url = "/subscription")
)
@SpringBootApplication
@EnableDiscoveryClient
public class SubscriptionApplication {

    /**
     * Входная точка в приложение.
     *
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        SpringApplication.run(SubscriptionApplication.class, args);
    }

}

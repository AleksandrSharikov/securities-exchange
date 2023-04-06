package com.exchangeinformant.subscription.service;


import com.exchangeinformant.subscription.util.rabbitMQ.PaymentProofService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

/**
 * Конфигурационный класс, предоставляющий консьюмера для сообщения из платежного сервиса.
 */
@Configuration
public class MessageFromPaymentService {
    /**
     * Метод, который возвращает консьюмера для сообщения из платежного сервиса.
     *
     * @return консьюмера, который будет принимать сообщения из платежного сервиса.
     */
    @Bean
    public Consumer<Message<PaymentProofService>> getPaymentProofService() {
        return (paymentProof) -> System.out.printf("Hi from listener! %s \n", paymentProof.getPayload());
    }
}

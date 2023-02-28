package com.exchangeinformant.subscription.config;


import com.exchangeinformant.subscription.service.LogicForPaymentProofService;
import com.exchangeinformant.subscription.util.rabbitMQ.PaymentProof;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class MessageFromPayment {

    LogicForPaymentProofService logicForPaymentProofService;

    @Bean
    public Consumer<Message<PaymentProof>> getPaymentProof() {
        return (paymentProof) -> {
            PaymentProof paymentProofFromMessage = new PaymentProof();

            paymentProofFromMessage.setSubscription_id(paymentProof.getPayload().getSubscription_id());
            paymentProofFromMessage.setPay_amount(paymentProof.getPayload().getPay_amount());
            paymentProofFromMessage.setExt_id(paymentProof.getPayload().getExt_id());
            logicForPaymentProofService.logicForPayment(paymentProofFromMessage);

            System.out.printf("Hi from listener! %s \n", paymentProof.getPayload());
        };
    }
}








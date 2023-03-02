package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import com.exchangeinformant.subscription.model.Subscription;
import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.repository.SubscriptionRepository;
import com.exchangeinformant.subscription.util.Timer.TimerSubscriptionEnd;
import com.exchangeinformant.subscription.util.enums.Status;
import com.exchangeinformant.subscription.util.error.MessageError;
import com.exchangeinformant.subscription.util.rabbitMQ.PaymentProof;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;

public class LogicForPaymentProofService {
    SubscriptionServiceImpl subscriptionServiceImpl;
    TimerSubscriptionEnd timerSubscriptionEnd;
    SubscriptionDTO subscriptionDTO;
    MessageError messageError;
    Tariff tariff;

    public void logicForPayment(PaymentProof paymentProof) {
        subscriptionDTO = subscriptionServiceImpl.getSubscription(paymentProof.getSubscription_id());
        if (tariff.getTariffCost() != Integer.parseInt(paymentProof.getPay_amount())) {
            if (subscriptionDTO.getStatus() != Status.ACTIVE) {
                subscriptionDTO.setStatus(Status.PAYMENT_ERROR);
                subscriptionDTO.setErrorDescription(messageError.createErrorDescription("Оплата не соответствует заданному тарифу и подписка не активна"));
                subscriptionServiceImpl.updateSubscription(subscriptionDTO);
            }
        } else {
            if (tariff.getTariffCost() == Integer.parseInt(paymentProof.getPay_amount())) {
                subscriptionDTO.setStatus(Status.ACTIVE);
                subscriptionDTO.setPrice(Integer.parseInt(paymentProof.getPay_amount()));
                subscriptionDTO.setExpiresAt(timerSubscriptionEnd.methodOfExpiresSub(subscriptionDTO.getPrice()));
                subscriptionServiceImpl.updateSubscription(subscriptionDTO);
            } else {
                if (subscriptionDTO.getStatus() == Status.ACTIVE) {
                    subscriptionDTO.setErrorDescription("Подписка уже активна");
                    subscriptionDTO.setStatus(Status.PAYMENT_ERROR);
                    subscriptionServiceImpl.updateSubscription(subscriptionDTO);
                }
            }
        }
    }
}










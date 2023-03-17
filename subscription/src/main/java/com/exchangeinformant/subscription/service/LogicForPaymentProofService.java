package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.util.Timer.TimerSubscriptionEnd;
import com.exchangeinformant.subscription.util.enums.Status;
import com.exchangeinformant.subscription.util.error.MessageError;
import com.exchangeinformant.subscription.util.rabbitMQ.PaymentProofService;

public class LogicForPaymentProofService {
    SubscriptionServiceImpl subscriptionServiceImpl;
    TimerSubscriptionEnd timerSubscriptionEnd;
    SubscriptionDTO subscriptionDTO;
    MessageError messageError;
    Tariff tariff;

    public void logicForPayment(PaymentProofService paymentProofService) {
        subscriptionDTO = subscriptionServiceImpl.getSubscription(paymentProofService.getSubscription_id());
        if (tariff.getTariffCost() != Integer.parseInt(paymentProofService.getPay_amount())) {
            if (subscriptionDTO.getStatus() != Status.ACTIVE) {
                subscriptionDTO.setStatus(Status.PAYMENT_ERROR);
                subscriptionDTO.setErrorDescription(messageError.createErrorDescription("Оплата не соответствует заданному тарифу и подписка не активна"));
                subscriptionServiceImpl.updateSubscription(subscriptionDTO);
            }
        } else {
            if (tariff.getTariffCost() == Integer.parseInt(paymentProofService.getPay_amount())) {
                subscriptionDTO.setStatus(Status.ACTIVE);
                subscriptionDTO.setPrice(Integer.parseInt(paymentProofService.getPay_amount()));
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










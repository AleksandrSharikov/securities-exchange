package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.dto.SubscriptionDTO;
import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.util.Timer.TimerSubscriptionEnd;
import com.exchangeinformant.subscription.util.enums.Status;
import com.exchangeinformant.subscription.util.error.MessageError;
import com.exchangeinformant.subscription.util.rabbitMQ.PaymentProofService;
import org.springframework.stereotype.Service;

/**
 * Класс, реализующий логику для сервиса проверки оплаты.
 */
@Service
public class LogicForPaymentProofService {
    /**
     * Сервис подписок.
     */
    private SubscriptionServiceImpl subscriptionServiceImpl;
    /**
     * Таймер окончания подписки.
     */
    private TimerSubscriptionEnd timerSubscriptionEnd;
    /**
     * DTO объект подписки.
     */
    private SubscriptionDTO subscriptionDTO;
    /**
     * Объект ошибки сообщения.
     */
    private MessageError messageError;
    /**
     * Объект тарифа.
     */
    private Tariff tariff;

    /**
     * Метод для проверки условий оплаты подписки и изменения статуса подписки.
     *
     * @param paymentProofService объект, содержащий информацию об оплате подписки.
     */
    public void logicForPayment(final PaymentProofService paymentProofService) {
        subscriptionDTO = subscriptionServiceImpl.getSubscription(paymentProofService.getSubscription_id());
        if (tariff.getTariffCost() != Integer.parseInt(paymentProofService.getPay_amount())) {
            if (subscriptionDTO.getStatus() != Status.ACTIVE) {
                subscriptionDTO.setStatus(Status.PAYMENT_ERROR);
                subscriptionDTO.setErrorDescription(messageError.createErrorDescription(
                        "Оплата не соответствует заданному тарифу и подписка не активна"));
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










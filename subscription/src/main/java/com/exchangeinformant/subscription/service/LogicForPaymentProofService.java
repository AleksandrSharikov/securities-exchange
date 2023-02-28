package com.exchangeinformant.subscription.service;

import com.exchangeinformant.subscription.model.Subscription;
import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.repository.SubscriptionRepository;
import com.exchangeinformant.subscription.util.enums.Status;
import com.exchangeinformant.subscription.util.rabbitMQ.PaymentProof;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDateTime;

public class LogicForPaymentProofService {
    SubscriptionRepository sr;
    Subscription sb;
    Tariff tariff;

    public void logicForPayment(PaymentProof pp) {
        sb = getSubscriptionById(pp.getSubscription_id());
        if (tariff.getTariffCost() != Integer.parseInt(pp.getPay_amount())) {
            if (sb.getStatus() != Status.ACTIVE) {
                sb.setStatus(Status.PAYMENT_ERROR);
                sb.setErrorDescription(createErrorDescription("Оплата не соответствует заданному тарифу и подписка не активна"));
                updateSubscription(sb.getErrorDescription(), sb.getStatus(), pp.getSubscription_id());
            }
        } else {
            if (tariff.getTariffCost() == Integer.parseInt(pp.getPay_amount())) {
                sb.setStatus(Status.ACTIVE);
                sb.setPrice(Integer.parseInt(pp.getPay_amount()));
                sb.setExpiresAt(methodOfExpiresSub(sb.getPrice()));
                updateSubscriptionExpiresPrice(sb.getErrorDescription(), sb.getStatus(), sb.getExpiresAt(), sb.getPrice(), pp.getSubscription_id());
            } else {
                if (sb.getStatus() == Status.ACTIVE) {
                    sb.setErrorDescription("Подписка уже активна");
                    sb.setStatus(Status.PAYMENT_ERROR);
                    updateSubscription(sb.getErrorDescription(), sb.getStatus(), pp.getSubscription_id());
                }
            }
        }
    }

    private Subscription getSubscriptionById(Long id) {
        return sr.findById(id).orElse(null);
    }

    private String createErrorDescription(String message) {
        LocalDateTime ldt = LocalDateTime.now();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error ( ")
                .append(message)
                .append(", ")
                .append(ldt)
                .append(" )");
        return stringBuilder.toString();
    }

    @Modifying
    @Query(value = "UPDATE Subscription s SET s.errorDescription = ?1, s.status = ?2 WHERE s.id = ?3")
    public void updateSubscription(String errorDescription, Status status, long id) {

    }

    @Modifying
    @Query(value = "UPDATE Subscription s SET s.errorDescription = ?1, s.status = ?2, s.expiresAt = ?3, s.price = ?4 WHERE s.id = ?5")
    public void updateSubscriptionExpiresPrice(String errorDescription, Status status, LocalDateTime expiresAt, int price, long id) {

    }


    public LocalDateTime methodOfExpiresSub(int price) {
        int days;
        int months;
        int years;

        int tariffCost14Days = sb.getTariff().getTariffCost();
            switch (price) {
                case 100: {
                    days = 14;
                    return LocalDateTime.now().plusYears(0).plusMonths(0).plusDays(days);
                }
                case 190: {
                    months = 1;
                    return LocalDateTime.now().plusYears(0).plusMonths(months).plusDays(0);
                }
                case 400: {
                    months = 3;
                    return LocalDateTime.now().plusYears(0).plusMonths(months).plusDays(0);
                }
                case 1200: {
                    years = 1;
                    return LocalDateTime.now().plusYears(years).plusMonths(0).plusDays(0);
                }
            }
        return null;
    }
}










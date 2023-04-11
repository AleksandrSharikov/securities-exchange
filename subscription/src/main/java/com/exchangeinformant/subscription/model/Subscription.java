package com.exchangeinformant.subscription.model;

import com.exchangeinformant.subscription.util.enums.Interval;
import com.exchangeinformant.subscription.util.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * Класс Subscription представляет собой объект подписки, содержащий информацию о тарифе, датах начала и окончания,
 * количестве отправляемых смс-сообщений и промо-коде, связанным с подпиской.
 */
@Schema(name = "Подписка", description = "Сущность подписки")
@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name = "subscriptions")
public class Subscription {

    /**
     * Уникальный идентификатор этой подписки.
     */
    @Id
    @Column(name = "subscription_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Тариф, связанный с данной подпиской.
     */
    @OneToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    /**
     * Дата создания подписки.
     */
    @Column(name = "create_at")
    private LocalDateTime createdAt;

    /**
     * Дата последнего обновления подписки.
     */
    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    /**
     * Дата начала подписки.
     */
    @Column(name = "start_at")
    private LocalDateTime startAt;

    /**
     * Дата окончания подписки.
     */
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    /**
     * Статус подписки.
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * Формат интервала подписки.
     */
    @Column(name = "interval_format")
    @Enumerated(EnumType.STRING)
    private Interval interval;

    /**
     * Количество интервалов подписки.
     */
    @Column(name = "interval_count")
    private int intervalCount;

    /**
     * Цена подписки.
     */
    @Column(name = "price")
    private int price;

    /**
     * Количество отправляемых смс-сообщений.
     */
    @Column(name = "send_sms")
    private int sendSMS;

    /**
     * Уникальный идентификатор пользователя, связанного с данной подпиской.
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * Флаг, указывающий, является ли данная подписка промо-подпиской.
     */
    @Column(name = "is_promo")
    private Boolean isPromo;

    /**
     * Описание ошибки, возникшей при создании/обновлении подписки.
     */
    @Column(name = "error_description")
    private String errorDescription;

    /**
     * Промо-подписка, связанная с данной подпиской.
     */
    @OneToOne
    @JoinColumn(name = "promocode_id")
    private PromoSubscription promoSubscription;


    /**
     * Конструктор Subscription, создающий новый пустой объект.
     */
    public Subscription() {

    }
}

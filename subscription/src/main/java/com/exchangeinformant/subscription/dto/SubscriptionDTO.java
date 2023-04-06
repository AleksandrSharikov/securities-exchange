package com.exchangeinformant.subscription.dto;

import com.exchangeinformant.subscription.model.PromoSubscription;
import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.util.enums.Interval;
import com.exchangeinformant.subscription.util.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Класс представляет объект DTO для сущности "Подписка".
 */
@Data
@Mapper
@Validated
@Schema(name = "Подписка", description = "Сущность подписки")
public class SubscriptionDTO {
    /**
     * Идентификатор подписки.
     */
    @Schema(description = "Идентификатор подписки")
    private Long id;
    /**
     * Тариф подписки.
     */
    @Schema(description = "Тариф подписки")
    @NotNull(message = "tariff shouldn't be null")
    private Tariff tariff;
    /**
     * Дата создания подписки.
     */
    @Schema(description = "Дата создания подписки")
    @NotNull(message = "data shouldn't be null")
    private LocalDateTime createdAt;
    /**
     * Дата изменения подписки.
     */
    @Schema(description = "Дата изменения подписки")
    @NotNull(message = "data shouldn't be null")
    private LocalDateTime updatedAt;
    /**
     * Дата активации подписки.
     */
    @Schema(description = "Дата активации подписки")
    @NotNull(message = "data shouldn't be null")
    private LocalDateTime startAt;
    /**
     * Дата истечения подписки.
     */
    @Schema(description = "Дата истечения подписки")
    @NotNull(message = "data shouldn't be null")
    private LocalDateTime expiresAt;
    /**
     * Статус подписки.
     */
    @Schema(description = "Статус подписки")
    @NotNull(message = "status shouldn't be null")
    private Status status;
    /**
     * Формат интервала.
     */
    @Schema(description = "Формат интервала")
    @NotNull(message = "interval shouldn't be null")
    private Interval interval;
    /**
     * Размер интервала.
     */
    @Schema(description = "Размер интервала")
    @NotNull(message = "interval_count shouldn't be null")
    private int intervalCount;
    /**
     * Цена.
     */
    @Schema(description = "Цена")
    @NotNull(message = "price shouldn't be null")
    private int price;
    /**
     * Отправка смс.
     */
    @Schema(description = "Отправка смс")
    private int sendSMS;
    /**
     * Идентификатор владельца.
     */
    @Schema(description = "Идентификатор владельца")
    private int userId;
    /**
     * Действительность промоподписки.
     */
    @Schema(description = "Действительность промоподписки")
    private Boolean isPromo;
    /**
     * Описание ошибки платежа.
     */
    @Schema(description = "Описание ошибки платежа")
    private String errorDescription;
    /**
     * Промоподписка.
     */
    @Schema(description = "Промоподписка")
    private PromoSubscription promoSubscription;
}

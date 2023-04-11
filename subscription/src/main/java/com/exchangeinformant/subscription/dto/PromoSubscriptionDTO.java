package com.exchangeinformant.subscription.dto;

import com.exchangeinformant.subscription.util.enums.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mapstruct.Mapper;

/**
 * Класс, представляющий сущность промоподписки.
 */
@Mapper
@Data
@Schema(name = "Промоподписка", description = "Сущность промоподписки")
public class PromoSubscriptionDTO {

    /**
     * Идентификатор промоподписки.
     */
    @Schema(description = "Идентификатор промоподписки")
    private Long id;

    /**
     * Активность промоподписки.
     */
    @Schema(description = "Активность промоподписки")
    private Boolean isPromocodeApplied;

    /**
     * Промокод.
     */
    @Schema(description = "Промокод")
    private String promocode;

    /**
     * Тип промокода.
     */
    @Schema(description = "Тип промокода")
    private Type type;
}

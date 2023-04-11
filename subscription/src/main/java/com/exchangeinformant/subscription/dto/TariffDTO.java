package com.exchangeinformant.subscription.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

/**
 * Класс, представляющий сущность тарифов.
 */
@Data
@Mapper
@Validated
@Schema(name = "Тарифы", description = "Тарифы на подписки")
public class TariffDTO {
    /**
     * Идентификатор тарифа.
     */
    @Schema(description = "Идентификатор тарифа")
    private Long id;
    /**
     * Наименование тарифа.
     */
    @NotNull(message = "title shouldn't be null")
    @Schema(description = "Наименование тарифа")
    private String title;
    /**
     * Описание тарифа.
     */
    @NotNull(message = "description shouldn't be null")
    @Schema(description = "Описание тарифа")
    private String description;
    /**
     * Тип тарифа.
     */
    @NotNull(message = "type shouldn't be null")
    @Schema(description = "Тип тарифа")
    private String type;
    /**
     * Активность тарифа.
     */
    @Schema(description = "Активность тарифа")
    private Boolean isActive;
}

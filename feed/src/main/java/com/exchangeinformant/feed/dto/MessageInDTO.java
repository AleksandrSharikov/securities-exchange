package com.exchangeinformant.feed.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
/**
 * Сообщение получаемое с других сервисов
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageInDTO {
    /**
     * id пользователя адресата сообщения
     */
    @Schema(description = "id пользователя адресата сообщения")
    private long userId;
    /**
     * id сервиса отправившего сообщение
     */
    @Schema(description = "id сервиса отправившего сообщение")
    private int sourceId;
    /**
     * сообщение
     */
    @Schema(description = "сообщение")
    private String data;

}

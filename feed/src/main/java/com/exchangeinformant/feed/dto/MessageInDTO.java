package com.exchangeinformant.feed.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageInDTO {
    // Сообщение получаемое с других сервисов
    @Schema(description = "id пользователя адресата сообщения")
    private long userId;

    @Schema(description = "id сервиса отправившего сообщение")
    private int sourceId;

    @Schema(description = "сообщение")
    private String data;

}

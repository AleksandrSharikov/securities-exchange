package com.exchangeinformant.feed.dto;

// Сообщение получаемое с других сервисов

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageInDTO {
    @Schema(description = "id адресата сообщения")
    private long userId;

    @Schema(description = "id сервиса отправившего сообщение")
    private int sourceId;

    @Schema(description = "сообщение")
    private String data;

}

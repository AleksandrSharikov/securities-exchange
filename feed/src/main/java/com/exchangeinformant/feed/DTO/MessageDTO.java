package com.exchangeinformant.feed.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageDTO {
    @Schema(description = "id адресата сообщения")
    private long userId;

    @Schema(description = "id сервиса отправившего сообщение")
    private int sourceId;

    @Schema(description = "сообщение")
    private String data;

}

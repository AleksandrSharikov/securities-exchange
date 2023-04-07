package com.exchangeinformant.feed.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
/**
 * Message received from other services
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MessageInDTO {
    /**
     * id of the user to which the message is addressed
     */
    @Schema(description = "id пользователя адресата сообщения")
    private long userId;
    /**
     * id of service that send the message
     */
    @Schema(description = "id сервиса отправившего сообщение")
    private int sourceId;
    /**
     * The message
     */
    @Schema(description = "сообщение")
    private String data;

}

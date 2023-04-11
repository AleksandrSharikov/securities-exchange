package com.exchangeinformant.feed.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Message for list of all messages list
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(name = "Выходное сообщение", description = "Сообщение передаваемое на ленту")
public class MessagePreDTO {

    @Schema(description = "id Сообщения")
    private long messageID;
    @Schema(description = "Флаг прочитанности сообщения")
    private boolean unread;
    @Schema(description = "Непосредственно подготовленное сообщение")
    private String message;

}

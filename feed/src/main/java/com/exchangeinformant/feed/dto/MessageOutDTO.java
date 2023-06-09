package com.exchangeinformant.feed.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * The model of a message to be sent to the front end
 */
@Data
@AllArgsConstructor
@Getter
@Setter
@Schema(name = "Выходное сообщение", description = "Сообщение передаваемое на ленту")
public class MessageOutDTO {
    @Schema(description = "Непосредственно подготовленное сообщение")
    private String message;

}

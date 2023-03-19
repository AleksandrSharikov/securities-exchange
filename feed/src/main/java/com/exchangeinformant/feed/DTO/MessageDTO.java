package com.exchangeinformant.feed.DTO;


import com.exchangeinformant.feed.model.Rank;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;

import java.time.LocalDateTime;

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

package com.exchangeinformant.feed.model;

// Сообщение для хранения в БД

import com.exchangeinformant.feed.dto.MessageInDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;



@Entity
@Table(name = "feed")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {

    public Message(MessageInDTO messageInDTO){
        this();
        this.userId = messageInDTO.getUserId();
        this.sourceId = messageInDTO.getSourceId();
        this.data = messageInDTO.getData();
        this.rank = 2;
        this.unread = true;

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "unread")
    private boolean unread = true;

    @Column(name = "time")
    private LocalDateTime receivingTime;

    @Column(name = "source_id")
    private int sourceId;

    @Column(name = "type_id")
    private int type_id;

    @Column(name =  "rank") // Важность сообщений, в моём представлении как параметр фильтрации, но у коллег есть сомнения
    private int rank;       // относительно необходимости данного поля

    @Column(name = "data")  // May be it is better to store Object here
    private String data;

}

// Сообщение для хранения в БД

package com.exchangeinformant.feed.model;

import com.exchangeinformant.feed.DTO.MessageDTO;
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

    public Message(MessageDTO messageDTO){
        this();
        this.userId = messageDTO.getUserId();
        this.sourceId = messageDTO.getSourceId();
        this.data = messageDTO.getData();
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

    @Column(name = "source")
    private int sourceId;

    @Column(name = "type")
    private String type;

    @Column(name =  "rank")
    private int rank;

    @Column(name = "data")  // May be it is better to store Object here
    private String data;

}

package com.exchangeinformant.feed.model;

import com.exchangeinformant.feed.DTO.MessageDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.autoconfigure.web.WebProperties;

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
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @JsonIgnore
    private long id;

    @Column(name = "user")
    private long userId;

    @Column(name = "unread")
    private boolean unread = true;

    @Column(name = "time")
    private LocalDateTime receivingTime;

    @Column(name = "source")
    private int sourceId;

    @Column(name = "type")
    private String type;

    @Column(name =  "rank")
    private Rank rank;

    @Column(name = "data")  // May be it is better to store Object here
    private String data;

}

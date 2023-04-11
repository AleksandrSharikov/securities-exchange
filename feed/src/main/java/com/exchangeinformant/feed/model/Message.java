package com.exchangeinformant.feed.model;


import com.exchangeinformant.feed.dto.MessageInDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


/**
 * The message to be stored in DB
 */
@Entity
@Table(name = "feed")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
    /**
     * The constructor to build message from messageInDTO to avoid creating mapper for simple operation
     *
     * @param messageInDTO Message received  from another service
     */
    public Message(MessageInDTO messageInDTO) {
        this();
        this.userId = messageInDTO.getUserId();
        this.sourceId = messageInDTO.getSourceId();
        this.data = messageInDTO.getData();
        this.rank = 2;
        this.unread = true;

    }

    /**
     * Messages id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;
    /**
     * Id of the user to whom the message is addressed
     */
    @Column(name = "user_id")
    private Long userId;
    /**
     * The unread message flag (messages are stored in the DB even after they are read)
     */
    @Column(name = "unread")
    private boolean unread = true;
    /**
     * The message receiving time (Indeed the time of receiving from RabbitMQ)
     */
    @Column(name = "time")
    private LocalDateTime receivingTime;
    /**
     * Id of the service sender
     */
    @Column(name = "source_id")
    private int sourceId;
    /**
     * Message type (for the search of patterns in the DB if they are presented)
     */
    @Column(name = "type_id")
    private int type_id;
    /**
     * The importance rank of the message. The higher it is more important the message
     */
// Не уверен в необходимости данного поля
    @Column(name = "rank")
    private int rank;
    /**
     * Data of message
     */
    @Column(name = "data")
    private String data;

}

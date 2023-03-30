package com.exchangeinformant.feed.model;


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

// Сообщение для хранения в БД

    // Конструктор из входного сообщения, что бы не создавать мэппер для элементарной операции
    public Message(MessageInDTO messageInDTO){
        this();
        this.userId = messageInDTO.getUserId();
        this.sourceId = messageInDTO.getSourceId();
        this.data = messageInDTO.getData();
        this.rank = 2;
        this.unread = true;

    }

// ID сообщения
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

// Id пользаователя, которому оно адресовано
    @Column(name = "user_id")
    private Long userId;

// Флаг непрочитанного сообщения (сообщения хранятся в БД даже когда были прочитаны)
    @Column(name = "unread")
    private boolean unread = true;

// Время получения (на самом деле получения из очереди, но думаю, они различаются на доли секунды)
    @Column(name = "time")
    private LocalDateTime receivingTime;

// Id сервиса отправившего сообщение
    @Column(name = "source_id")
    private int sourceId;

// Тип сообщения (по нему в БД находится шаблон, если есть)
    @Column(name = "type_id")
    private int type_id;

// Важность сообщения. Чем тут цифра больше, тем важнее. Используется  для фильтрации
// Не уверен в необходимости данного поля
    @Column(name =  "rank") // Важность сообщений, в моём представлении как параметр фильтрации, но у коллег есть сомнения
    private int rank;       // относительно необходимости данного поля

// Собственно данные.  Можно заменить на Object или List<String>, но, мне кажется просто текст удобнее
    @Column(name = "data")
    private String data;

}

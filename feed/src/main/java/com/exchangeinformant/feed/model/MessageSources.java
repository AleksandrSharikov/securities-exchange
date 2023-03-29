package com.exchangeinformant.feed.model;

// Модель для хранения в БД информации об источниках сообщений

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "sources")
public class MessageSources {

// Список источников сообщений
    @Id
    @Column(name = "source_id")
    private int sourceID;

// Важность выставляемая для сообщений из этого источника
    @Column(name = "default_rank")
    private int defaultRank;
    @Column(name = "source_name")
    private String name;
}

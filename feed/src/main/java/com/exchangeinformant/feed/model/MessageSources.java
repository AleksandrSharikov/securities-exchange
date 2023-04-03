package com.exchangeinformant.feed.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Модель для хранения в БД информации об источниках сообщений
 */
@Data
@Getter
@Setter
@Entity
@Table(name = "sources")
public class MessageSources {

    /**
     * Список источников сообщений
     */
    @Id
    @Column(name = "source_id")
    private int sourceID;
    /**
     * Важность выставляемая для сообщений из этого источника
     */
    @Column(name = "default_rank")
    private int defaultRank;
    /**
     * Название источника сообщений
     */
    @Column(name = "source_name")
    private String name;
}

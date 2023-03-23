// Модель для хранения в БД информации об источниках сообщений

package com.exchangeinformant.feed.model;

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
    @Id
    @Column(name = "source_id")
    private int sourceID;
    @Column(name = "default_rank")
    private int defaultRank;
    @Column(name = "source_name")
    private String name;
}

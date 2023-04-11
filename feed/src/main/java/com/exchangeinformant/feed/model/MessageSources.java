package com.exchangeinformant.feed.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Model for storage in the DB of the message sources
 */
@Data
@Getter
@Setter
@Entity
@Table(name = "sources")
public class MessageSources {

    /**
     * The list of the messages sources
     */
    @Id
    @Column(name = "source_id")
    private int sourceID;
    /**
     * Default rank for this source
     */
    @Column(name = "default_rank")
    private int defaultRank;
    /**
     * Source name
     */
    @Column(name = "source_name")
    private String name;
}

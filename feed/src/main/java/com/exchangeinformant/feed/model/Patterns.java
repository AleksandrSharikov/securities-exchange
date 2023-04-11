package com.exchangeinformant.feed.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

/**
 * DB of the messsages paterns
 */
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "text_patterns")
public class Patterns {
    /**
     * ID
     */
    @Id
    @Column(name = "type_id")
    int id;
    /**
     * Name of this type of messages (Eg: News, or Transactions...)
     */
    @Column(name = "type")
    String type;
    /**
     * Patterns for String.format
     */
    @Column(name = "pattern")
    String pattern;
}

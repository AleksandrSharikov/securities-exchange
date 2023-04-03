package com.exchangeinformant.feed.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

/**
 * База шаблонов для сообщений
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
     * Название сообщений такого типа
     */
    @Column(name = "type")
    String type;
    /**
     * Шаблон для String.format
     */
    @Column(name = "pattern")
    String pattern;
}

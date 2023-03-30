package com.exchangeinformant.feed.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "text_patterns")
public class Patterns {

    // База шаблонов для сообщений
    @Id
    @Column(name = "type_id")
    int id;

// Просто название сообщений такого типа
    @Column(name = "type")
    String type;

// Шаблон для String.format
    @Column(name = "pattern")
    String pattern;
}

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
    @Id
    @Column(name = "type_id")
    int id;

    @Column(name = "type")
    String type;

    @Column(name = "pattern")
    String pattern;
}

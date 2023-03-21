package com.example.notifier.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Шаблон представляет собой текст, в который включены переменные в формате ${name_var} (name_var это имя данной
 * переменной). Данные переменный будут заменены классом MessageBuilder, на требуемые данные, которые поступают в
 * формате json
 */
@Entity(name = "notifier_templates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int id;

    @Column
    String text;

}

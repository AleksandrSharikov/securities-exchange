package com.example.notifier.model;

import com.example.notifier.dto.TemplateDto;
import jakarta.persistence.*;
import lombok.*;

/**
 * Шаблон представляет собой текст, в который включены переменные в формате ${name_var} (name_var это имя данной
 * переменной). Данные переменный будут заменены классом MessageManager, на требуемые данные, которые поступают в
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
    Long id;

    @Column
    String text;

    public Template(TemplateDto templateDto) {
        this.id = templateDto.getId();
        this.text = templateDto.getText();
    }
}

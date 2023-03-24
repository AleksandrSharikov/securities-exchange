package com.example.notifier.dto;

import com.example.notifier.model.Template;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TemplateDto {
    Long id;
    String text;

    public TemplateDto(Template template) {
        this.id = template.getId();
        this.text = template.getText();
    }
}

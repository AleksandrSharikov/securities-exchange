package com.example.notifier.dto;

import com.example.notifier.model.Template;
import lombok.*;

@Data
@Builder
public class TemplateDto {
    Long id;
    String text;

    public TemplateDto(Template template) {
        this.id = template.getId();
        this.text = template.getText();
    }
}

package com.example.notifier.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDto {
    Long id;
    private String subject;
    String text;
}

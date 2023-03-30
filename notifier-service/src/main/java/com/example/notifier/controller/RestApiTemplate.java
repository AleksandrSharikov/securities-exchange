package com.example.notifier.controller;

import com.example.notifier.dto.TemplateDto;
import com.example.notifier.mapper.TemplateMapper;
import com.example.notifier.model.Template;
import com.example.notifier.service.TemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/template")
@AllArgsConstructor
public class RestApiTemplate {
    private final TemplateService templateService;

    // TODO добавить на контроллеры свагер, оперейшн, роли
    @GetMapping("{id}")
    public ResponseEntity<TemplateDto> getTemplate(@PathVariable long id) {
        return ResponseEntity.ok(TemplateMapper.INSTANCE.templateToDto(templateService.getTemplateById(id)));
    }

    @GetMapping
    public ResponseEntity<List<TemplateDto>> getAllTemplate() {
        return ResponseEntity.ok(TemplateMapper.INSTANCE.templatesToDto(templateService.getAllTemplate()));
    }

    @PostMapping
    public ResponseEntity<TemplateDto> addTemplate(@RequestBody TemplateDto templateDto) {
        Template template = TemplateMapper.INSTANCE.templateFromDto(templateDto);
        templateService.saveTemplate(template);
        return ResponseEntity.ok(TemplateMapper.INSTANCE.templateToDto(template));
    }

    @PutMapping
    public ResponseEntity<TemplateDto> updateTemplate(@RequestBody TemplateDto templateDto) {
        Template template = TemplateMapper.INSTANCE.templateFromDto(templateDto);
        templateService.saveTemplate(template);
        return ResponseEntity.ok(TemplateMapper.INSTANCE.templateToDto(template));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteTemplate(@PathVariable long id) {
        templateService.deleteTemplateById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

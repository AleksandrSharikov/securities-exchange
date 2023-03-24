package com.example.notifier.controller;

import com.example.notifier.dto.TemplateDto;
import com.example.notifier.model.Template;
import com.example.notifier.service.TemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/template")
public class RestApiTemplate {
    private final TemplateService templateService;

    public RestApiTemplate(TemplateService templateService) {
        this.templateService = templateService;
    }

    //TODO добавить на контроллеры свагер, оперейшн, роли
    @GetMapping("{id}")
    public ResponseEntity<TemplateDto> getTemplate(@PathVariable long id) {
        return ResponseEntity.ok(new TemplateDto(templateService.getTemplateById(id)));
    }

    @GetMapping
    public ResponseEntity<List<TemplateDto>> getAllTemplate() {
        return ResponseEntity.ok(templateService.getAllTemplate().stream()
                .map(TemplateDto::new)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<TemplateDto> addTemplate(@RequestBody TemplateDto templateDto) {
        Template template = new Template(templateDto);
        templateService.saveTemplate(template);
        return ResponseEntity.ok(new TemplateDto(template));
    }

    @PutMapping
    public ResponseEntity<TemplateDto> updateTemplate(@RequestBody TemplateDto templateDto) {
        Template template = new Template(templateDto);
        templateService.saveTemplate(template);
        return ResponseEntity.ok(new TemplateDto(template));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteTemplate(@PathVariable long id) {
        templateService.deleteTemplateById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

package com.example.notifier.service;

import com.example.notifier.model.Template;
import com.example.notifier.repository.TemplateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository templateRepository;

    @Transactional(readOnly = true)
    @Override
    public Template getTemplateById(long id) {
        return templateRepository.findById(id)
                .orElseThrow(() -> new NullPointerException(String.format("Template with ID %s not found", id)));
}

    @Transactional(readOnly = true)
    @Override
    public List<Template> getAllTemplate() {
        return templateRepository.findAll();
    }

    @Transactional
    @Override
    public void saveTemplate(Template template) {
        templateRepository.save(template);
    }

    @Transactional
    @Override
    public void deleteTemplateById(long id) {
        templateRepository.deleteById(id);
    }
}

package com.example.notifier.service;

import com.example.notifier.model.Template;
import com.example.notifier.repository.TemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository templateRepository;
    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Template getTemplateById(long id) {
        return templateRepository.findTemplateById(id);
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

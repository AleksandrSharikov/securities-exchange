package com.example.notifier.service;

import com.example.notifier.model.Template;
import com.example.notifier.repository.TemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;

    public TemplateServiceImpl(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    //TODO добавить транзакции где необходимо

    @Transactional(readOnly = true)
    @Override
    public Template getTemplateById(int id) {
        return templateRepository.findTemplateById(id);
    }
}

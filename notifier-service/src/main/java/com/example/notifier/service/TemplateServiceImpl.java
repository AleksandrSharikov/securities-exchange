package com.example.notifier.service;

import com.example.notifier.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mikhailov Artem
 */

@Service
public class TemplateServiceImpl implements TemplateService {

//    private final TemplateRepository templateRepository;
//
//    @Autowired
//    public TemplateServiceImpl(TemplateRepository templateRepository) {
//        this.templateRepository = templateRepository;
//    }


//    @Override
//    public Template findTemplateById(long id) {
//        return templateRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public void createTemplate(Template template) {
//        templateRepository.save(template);
//
//    }
//
//    @Override
//    public void updateTemplate(long id, Template Template) {
//        Template template = templateRepository.findById(id).orElse(null);
//        template.setTitle(template.getTitle());
//        template.setMessage(template.getMessage());
//        templateRepository.save(template);
//
//    }
//
//    @Override
//    public void deleteTemplate(long id) {
//        templateRepository.deleteById(id);
//
//    }
//
//    @Override
//    public List<Template> getAllTemplates() {
//        return templateRepository.findAll();
//    }
}

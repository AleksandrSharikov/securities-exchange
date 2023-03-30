package com.example.notifier.service;

import com.example.notifier.model.Template;

import java.util.LinkedList;
import java.util.List;

public interface TemplateService {

    Template getTemplateById(long id);
    List<Template> getAllTemplate();
    void saveTemplate(Template template);
    void deleteTemplateById(long id);
}

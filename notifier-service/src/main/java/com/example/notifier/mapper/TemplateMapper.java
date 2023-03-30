package com.example.notifier.mapper;

import com.example.notifier.dto.TemplateDto;
import com.example.notifier.model.Template;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TemplateMapper {

    TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);
    TemplateDto templateToDto(Template template);
    Template templateFromDto(TemplateDto templateDto);
    List<TemplateDto> templatesToDto(List<Template> templateList);
}

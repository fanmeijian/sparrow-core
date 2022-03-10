package cn.sparrow.permission.mgt.service.impl;

import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ConverterConfig implements WebMvcConfigurer{
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ModelAttributeIdConverter());
    }
}

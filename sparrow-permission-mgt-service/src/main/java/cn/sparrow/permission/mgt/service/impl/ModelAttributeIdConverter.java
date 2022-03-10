package cn.sparrow.permission.mgt.service.impl;

import java.io.Serializable;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;

import cn.sparrow.permission.model.resource.ModelAttribute;
import cn.sparrow.permission.model.resource.ModelAttributePK;

@Component
public class ModelAttributeIdConverter implements Converter<String, ModelAttributePK> {

    @Override
    public ModelAttributePK convert(String source) {
        String[] parts = source.split("_");
        ModelAttributePK pk = new ModelAttributePK();
        pk.setModelName(parts[0]);
        pk.setAttributeName(parts[1]);

        return pk;
    }
}

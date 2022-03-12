package cn.sparrow.permission.mgt.service.impl;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cn.sparrow.permission.model.resource.ModelAttributePK;

@Component
public class ModelAttributeIdConverter implements Converter<String, ModelAttributePK> {

    @Override
    public ModelAttributePK convert(String source) {
        String[] parts = source.split("_");
        ModelAttributePK pk = new ModelAttributePK();
        pk.setModelId(parts[0]);
        pk.setAttributeId(parts[1]);

        return pk;
    }
}

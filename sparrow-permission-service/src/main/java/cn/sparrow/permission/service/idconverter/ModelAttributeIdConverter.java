package cn.sparrow.permission.service.idconverter;

import java.io.Serializable;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;

import cn.sparrow.permission.model.ModelAttribute;
import cn.sparrow.permission.model.ModelAttributePK;

@Component
public class ModelAttributeIdConverter implements BackendIdConverter {
  @Override
  public boolean supports(Class<?> delimiter) {
    return ModelAttribute.class.equals(delimiter);
  }

  @Override
  public Serializable fromRequestId(String id, Class<?> entityType) {
    String[] parts = id.split(",");
    ModelAttributePK pk = new ModelAttributePK();
    pk.setModelName(parts[0]);
    pk.setAttributeName(parts[1]);

    return pk;
  }

  @Override
  public String toRequestId(Serializable id, Class<?> entityType) {
    ModelAttributePK pk = (ModelAttributePK) id;
    return String.format("%s,%s", pk.getModelName(), pk.getAttributeName());
  }
}

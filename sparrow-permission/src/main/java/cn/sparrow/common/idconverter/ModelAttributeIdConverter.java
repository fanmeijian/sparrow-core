package cn.sparrow.common.idconverter;

import java.io.Serializable;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;
import cn.sparrow.model.permission.ModelAttribute;
import cn.sparrow.model.permission.ModelAttributePK;

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
    pk.setName(parts[1]);

    return pk;
  }

  @Override
  public String toRequestId(Serializable id, Class<?> entityType) {
    ModelAttributePK pk = (ModelAttributePK) id;
    return String.format("%s,%s", pk.getModelName(), pk.getName());
  }
}

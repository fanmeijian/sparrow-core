package cn.sparrow.common.idconverter;

import java.io.Serializable;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;
import cn.sparrow.model.permission.UserUrlPermission;
import cn.sparrow.model.permission.UserUrlPermissionPK;

@Component
public class UserUrlPermissionIdConverter implements BackendIdConverter {
  @Override
  public boolean supports(Class<?> delimiter) {
    return UserUrlPermission.class.equals(delimiter);
  }

  @Override
  public Serializable fromRequestId(String id, Class<?> entityType) {
    String[] parts = id.split("_");
    UserUrlPermissionPK pk = new UserUrlPermissionPK();
    pk.setUrlId(parts[0]);
    pk.setUsername(parts[1]);

    return pk;
  }

  @Override
  public String toRequestId(Serializable id, Class<?> entityType) {
    UserUrlPermissionPK pk = (UserUrlPermissionPK) id;
    return String.format("%s_%s", pk.getUrlId(), pk.getUsername());
  }
}

package cn.sparrow.common.idconverter;

import java.io.Serializable;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;
import cn.sparrow.model.permission.SysroleUrlPermission;
import cn.sparrow.model.permission.SysroleUrlPermissionPK;

@Component
public class SysroleUrlPermissionIdConverter implements BackendIdConverter {
  @Override
  public boolean supports(Class<?> delimiter) {
    return SysroleUrlPermission.class.equals(delimiter);
  }

  @Override
  public Serializable fromRequestId(String id, Class<?> entityType) {
    return new SysroleUrlPermissionPK(id.split("_")[0],id.split("_")[1]);
  }

  @Override
  public String toRequestId(Serializable id, Class<?> entityType) {
    return String.format("%s_%s", ((SysroleUrlPermissionPK)id).getSysroleId(), ((SysroleUrlPermissionPK)id).getUrlId());
  }
}

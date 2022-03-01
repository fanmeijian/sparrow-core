package cn.sparrow.permission.idconverter;

import java.io.Serializable;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;

import cn.sparrow.permission.model.SysroleApiPK;
import cn.sparrow.permission.model.SysroleApiPermission;

@Component
public class SysroleApiIdConverter implements BackendIdConverter {
  @Override
  public boolean supports(Class<?> delimiter) {
    return SysroleApiPermission.class.equals(delimiter);
  }

  @Override
  public Serializable fromRequestId(String id, Class<?> entityType) {
    return new SysroleApiPK(id.split("_")[0],id.split("_")[1]);
  }

  @Override
  public String toRequestId(Serializable id, Class<?> entityType) {
    return String.format("%s_%s", ((SysroleApiPK)id).getSysroleId(), ((SysroleApiPK)id).getApiId());
  }
}

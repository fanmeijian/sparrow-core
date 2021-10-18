package cn.sparrow.model.permission;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionTypeEnum;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractPermission {
  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  protected PermissionEnum permission;
  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  protected PermissionTypeEnum permissionType;
}

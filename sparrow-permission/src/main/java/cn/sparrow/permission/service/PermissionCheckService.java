package cn.sparrow.permission.service;

import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.permission.PermissionExpression;

public interface PermissionCheckService<T, ID> {
  public boolean checkPermission(ID id, PermissionExpression<T, ID> permissionExpression, PermissionEnum permission);
}

package cn.sparrow.permission.service;

import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.permission.PermissionExpression;
import cn.sparrow.model.permission.PermissionToken;

public interface PermissionExpressionService {

  public boolean execute(PermissionExpression<?, ?> permissionExpression);
  
  public boolean containPermission(PermissionToken permissionToken, PermissionEnum permission);
}

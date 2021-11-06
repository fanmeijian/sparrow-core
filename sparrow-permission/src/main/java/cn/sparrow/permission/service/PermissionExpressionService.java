package cn.sparrow.permission.service;

import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.permission.PermissionToken;
import cn.sparrow.model.permission.token.PermissionExpression;

public interface PermissionExpressionService {

  public boolean execute(PermissionExpression<?, ?> permissionExpression);
  
  public boolean containPermission(PermissionToken permissionToken, PermissionEnum permission);
}

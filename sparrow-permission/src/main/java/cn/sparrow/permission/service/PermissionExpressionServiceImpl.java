package cn.sparrow.permission.service;

import org.springframework.stereotype.Service;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.permission.PermissionExpression;
import cn.sparrow.model.permission.PermissionToken;

@Service
public class PermissionExpressionServiceImpl implements PermissionExpressionService {

  @Override
  public boolean execute(PermissionExpression<?, ?> permissionExpression) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean containPermission(PermissionToken permissionToken, PermissionEnum permission) {
    // TODO Auto-generated method stub
    return false;
  }

}

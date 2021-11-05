package cn.sparrow.permission.service;

import org.springframework.stereotype.Service;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.group.Group;
import cn.sparrow.model.permission.token.PermissionExpression;

@Service
public class PermissionCheckGroupServiceImpl implements PermissionCheckService<Group, String> {

  @Override
  public boolean checkPermission(String id, PermissionExpression<Group, String> permissionExpression,
      PermissionEnum permission) {
    // TODO Auto-generated method stub
    return false;
  }


}

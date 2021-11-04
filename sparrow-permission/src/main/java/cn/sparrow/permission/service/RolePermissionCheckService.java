package cn.sparrow.permission.service;

import org.springframework.stereotype.Service;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.organization.OrganizationRole;
import cn.sparrow.model.organization.OrganizationRolePK;
import cn.sparrow.model.permission.token.PermissionExpression;

@Service
public class RolePermissionCheckService implements PermissionCheckService<OrganizationRole, OrganizationRolePK> {

  @Override
  public boolean checkPermission(OrganizationRolePK id,
      PermissionExpression<?, ?> permissionExpression, PermissionEnum permission) {
    // TODO Auto-generated method stub
    return false;
  }



}

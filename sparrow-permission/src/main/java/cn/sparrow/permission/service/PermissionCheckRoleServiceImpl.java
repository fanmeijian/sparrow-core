package cn.sparrow.permission.service;

import org.springframework.stereotype.Service;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.organization.OrganizationRole;
import cn.sparrow.model.organization.OrganizationRolePK;
import cn.sparrow.model.permission.PermissionExpression;

@Service
public class PermissionCheckRoleServiceImpl implements PermissionCheckService<OrganizationRole, OrganizationRolePK> {

  @Override
  public boolean checkPermission(OrganizationRolePK id,
      PermissionExpression<OrganizationRole, OrganizationRolePK> permissionExpression, PermissionEnum permission) {
    // TODO Auto-generated method stub
    return false;
  }



}

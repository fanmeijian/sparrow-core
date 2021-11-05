package cn.sparrow.permission.service;

import org.springframework.stereotype.Service;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.organization.OrganizationPositionLevel;
import cn.sparrow.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.model.permission.token.PermissionExpression;

@Service
public class PermissionCheckPositionLevelServiceImpl implements PermissionCheckService<OrganizationPositionLevel, OrganizationPositionLevelPK> {

  @Override
  public boolean checkPermission(OrganizationPositionLevelPK id,
      PermissionExpression<?, ?> permissionExpression, PermissionEnum permission) {
    // TODO Auto-generated method stub
    return false;
  }

}

package cn.sparrow.permission.service;

import java.util.Set;
import cn.sparrow.model.permission.AbstractDataFieldPermissionPK;

public class DataFieldPermissionService extends AbstractPermissionService<AbstractDataFieldPermissionPK> {

	@Override
	public boolean hasPermission(AbstractDataFieldPermissionPK target, String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConfigPermission(AbstractDataFieldPermissionPK target, PermissionTargetEnum permissionTarget) {
		// TODO Auto-generated method stub
		return false;
	}

  @Override
  public boolean addPermission(AbstractDataFieldPermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addPermissions(Set<AbstractDataFieldPermissionPK> targets,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delPermssion(AbstractDataFieldPermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delPermssions(AbstractDataFieldPermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

}

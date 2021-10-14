package cn.sparrow.permission.service;

import java.util.Set;
import cn.sparrow.model.permission.AbstractModelAttributePermissionPK;

public class ModelAttributePermissionService extends AbstractPermissionService<AbstractModelAttributePermissionPK> {


	@Override
	public boolean hasPermission(AbstractModelAttributePermissionPK target, String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConfigPermission(AbstractModelAttributePermissionPK target,
			PermissionTargetEnum permissionTarget) {
		// TODO Auto-generated method stub
		return false;
	}

  @Override
  public boolean addPermission(AbstractModelAttributePermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addPermissions(Set<AbstractModelAttributePermissionPK> targets,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delPermssion(AbstractModelAttributePermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delPermssions(AbstractModelAttributePermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

}

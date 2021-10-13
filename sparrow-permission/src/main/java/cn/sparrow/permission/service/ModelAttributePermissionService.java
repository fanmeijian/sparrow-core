package cn.sparrow.permission.service;

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

}

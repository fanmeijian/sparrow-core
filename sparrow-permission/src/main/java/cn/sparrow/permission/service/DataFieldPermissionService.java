package cn.sparrow.permission.service;

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

}

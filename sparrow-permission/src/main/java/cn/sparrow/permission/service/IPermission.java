package cn.sparrow.permission.service;

import java.util.Set;

import cn.sparrow.model.common.PermissionTargetEnum;

public interface IPermission<T> {

//	@Deprecated
//	public boolean isConfigPermission(T target);
	
	public boolean isConfigPermission(T permissionPK, PermissionTargetEnum permissionTarget);

	public boolean hasPermission(T permissionPK, String username);
	
	public boolean addPermission(T permissionPK, PermissionTargetEnum permissionTarget);

	public boolean addPermissions(Set<T> permissionPK, PermissionTargetEnum permissionTarget);
	
	public boolean delPermssion(T permissionPK, PermissionTargetEnum permissionTarget);
	
	public boolean delPermssions(T permissionPK, PermissionTargetEnum permissionTarget);
}

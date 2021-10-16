package cn.sparrow.permission.service;

import java.util.Set;

import cn.sparrow.model.common.PermissionTargetEnum;

public interface IPermission<T> {

//	@Deprecated
//	public boolean isConfigPermission(T target);
	
	public boolean isConfigPermission(T target, PermissionTargetEnum permissionTarget);

	public boolean hasPermission(T target, String username);
	
	public boolean addPermission(T target, PermissionTargetEnum permissionTarget);

	public boolean addPermissions(Set<T> targets, PermissionTargetEnum permissionTarget);
	
	public boolean delPermssion(T target, PermissionTargetEnum permissionTarget);
	
	public boolean delPermssions(T target, PermissionTargetEnum permissionTarget);
}

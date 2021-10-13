package cn.sparrow.permission.service;

public interface IPermission<T> {

//	@Deprecated
//	public boolean isConfigPermission(T target);
	
	public boolean isConfigPermission(T target, PermissionTargetEnum permissionTarget);

	public boolean hasPermission(T target, String username);
	
}

package cn.sparrow.permission.mgt.api;

import java.util.List;

public interface PreserveRole {
	String ROLE_SYSADMIN = "SYSADMIN";
	String ROLE_ADMIN = "ADMIN";
	String ROLE_USER = "USER";
	//有删除权限
	String ROLE_SUPER_SYSADMIN = "SUPER_SYSADMIN";
	//有删除权限
	String ROLE_SUPER_ADMIN = "SUPER_ADMIN";
	
	public List<String> getRoles();
}

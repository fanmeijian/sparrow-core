package cn.sparrow.permission.service;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.constant.PermissionTypeEnum;
import cn.sparrow.permission.service.organization.EmployeeToken;

public interface PermissionService {

	public boolean hasPermission(EmployeeToken employeeToken, PermissionToken permissionToken,
			PermissionEnum permission);

	public boolean hasPermission(String employeeId, String tokenId, PermissionEnum permission);

//是否配置了某个权限
	public boolean isConfigPermission(PermissionToken permissionToken, PermissionEnum permissionEnum);

	public boolean isConfigPermission(PermissionToken permissionToken, PermissionEnum permissionEnum,
			PermissionTypeEnum permissionTypeEnum);
}

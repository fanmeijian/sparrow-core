package cn.sparrow.permission.service;

import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionTypeEnum;
import cn.sparrow.organization.service.EmployeeToken;

public interface PermissionService {

	public boolean hasPermission(EmployeeToken employeeToken, PermissionToken permissionToken,
			PermissionEnum permission);

	public boolean hasPermission(String employeeId, String tokenId, PermissionEnum permission);

//是否配置了某个权限
	public boolean isConfigPermission(PermissionToken permissionToken, PermissionEnum permissionEnum);

	public boolean isConfigPermission(PermissionToken permissionToken, PermissionEnum permissionEnum,
			PermissionTypeEnum permissionTypeEnum);
}

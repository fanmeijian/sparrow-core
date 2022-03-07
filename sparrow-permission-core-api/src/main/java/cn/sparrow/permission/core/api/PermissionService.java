package cn.sparrow.permission.core.api;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.constant.PermissionTypeEnum;
import cn.sparrow.permission.model.token.EmployeeToken;
import cn.sparrow.permission.model.token.PermissionToken;

public interface PermissionService {

	public boolean hasPermission(EmployeeToken employeeToken, PermissionToken permissionToken,
			PermissionEnum permission);

	public boolean hasPermission(String employeeId, String tokenId, PermissionEnum permissionEnum);
	
	public boolean hasPermission(String username, PermissionToken permissionToken, PermissionEnum permissionEnum);

//是否配置了某个权限
	public boolean isConfigPermission(PermissionToken permissionToken, PermissionEnum permissionEnum);

	public boolean isConfigPermission(PermissionToken permissionToken, PermissionEnum permissionEnum,
			PermissionTypeEnum permissionTypeEnum);
}

package cn.sparrow.permission.service;

import cn.sparrow.model.permission.token.EmployeeToken;

public interface PermissionService<T> {
	public boolean hasPermission(EmployeeToken employeeToken, T permissionToken);
}

package cn.sparrow.permission.service;

import cn.sparrow.model.permission.token.EmployeeToken;
import cn.sparrow.model.permission.token.FuncPermissionToken;

public class FuncPermissionService implements PermissionService<FuncPermissionToken> {

	@Override
	public boolean hasPermission(EmployeeToken employeeToken, FuncPermissionToken permissionToken) {
		// TODO Auto-generated method stub
		return false;
	}

}

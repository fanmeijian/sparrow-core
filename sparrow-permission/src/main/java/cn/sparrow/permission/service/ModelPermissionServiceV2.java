package cn.sparrow.permission.service;

import cn.sparrow.model.permission.token.EmployeeToken;
import cn.sparrow.model.permission.token.ModelPermissionToken;

public class ModelPermissionServiceV2 implements PermissionService<ModelPermissionToken> {

	@Override
	public boolean hasPermission(EmployeeToken employeeToken, ModelPermissionToken permissionToken) {
		// TODO Auto-generated method stub
		return false;
	}

}

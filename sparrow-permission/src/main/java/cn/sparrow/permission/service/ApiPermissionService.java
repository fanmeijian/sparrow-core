package cn.sparrow.permission.service;

import org.springframework.stereotype.Service;

import cn.sparrow.model.permission.token.ApiPermissionToken;
import cn.sparrow.model.permission.token.EmployeeToken;

@Service
public class ApiPermissionService implements PermissionService<ApiPermissionToken> {

	@Override
	public boolean hasPermission(EmployeeToken employeeToken, ApiPermissionToken permissionToken) {
		// TODO Auto-generated method stub
		return false;
	}


}
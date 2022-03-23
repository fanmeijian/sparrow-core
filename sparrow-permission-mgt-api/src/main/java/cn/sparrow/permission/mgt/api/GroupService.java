package cn.sparrow.permission.mgt.api;

import java.util.List;

import cn.sparrow.permission.model.organization.Employee;

public interface GroupService extends GroupRestService{

	public List<Employee> getFinalEmployees(String groupId);
	
}

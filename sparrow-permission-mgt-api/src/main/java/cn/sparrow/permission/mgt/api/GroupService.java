package cn.sparrow.permission.mgt.api;

import java.util.List;

import cn.sparrow.permission.model.organization.Employee;

public interface GroupService extends GroupRestService{

//	@Operation(summary = "递归获取群组成员，最终为员工列表")
//	@GetMapping("/allEmployees")
//	@ResponseBody
	public List<Employee> getFinalEmployees(String groupId);
	
}

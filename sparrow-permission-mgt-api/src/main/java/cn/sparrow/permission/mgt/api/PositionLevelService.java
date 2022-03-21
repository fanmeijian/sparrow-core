package cn.sparrow.permission.mgt.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelation;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelationPK;

public interface PositionLevelService extends JobLevelRestService{

//	@Operation(summary = "获取下属职级")
//	@GetMapping("/children")
//	@ResponseBody
//	public List<OrganizationPositionLevel> getChildren(OrganizationPositionLevelPK organizationLevelId);

//	@Operation(summary = "获取上级职级")
//	@GetMapping("/parents")
//	@ResponseBody
//	public List<OrganizationPositionLevel> getParents(OrganizationPositionLevelPK organizationLevelId);

//	@Operation(summary = "设置职级关系")
//	@PostMapping("/relation")
//	@ResponseBody
	public void addRelation(@RequestBody List<OrganizationPositionLevelRelation> organizationLevelRelations);

//	@Operation(summary = "移除职级关系")
//	@DeleteMapping("/relation")
//	@ResponseBody
	public void removeRelation(@RequestBody List<OrganizationPositionLevelRelationPK> ids);

//	@Operation(summary = "获取拥有此岗位员工")
//	@GetMapping("/employees")
//	@ResponseBody
//	public List<Employee> getEmployees(OrganizationPositionLevelPK organizationLevelId);
}

package cn.sparrow.permission.mgt.api;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelation;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelationPK;
import cn.sparrow.permission.model.organization.PositionLevel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "职级服务")
@RequestMapping("/jobLevels")
public interface PositionLevelService extends JobLevelRestService{

//	@Operation(summary = "获取下属职级")
//	@GetMapping("/children")
//	@ResponseBody
//	public List<OrganizationPositionLevel> getChildren(OrganizationPositionLevelPK organizationLevelId);

//	@Operation(summary = "获取上级职级")
//	@GetMapping("/parents")
//	@ResponseBody
//	public List<OrganizationPositionLevel> getParents(OrganizationPositionLevelPK organizationLevelId);

	@Operation(summary = "获取所属组织")
	@GetMapping("/{levelId}/parentOrganizations")
	@ResponseBody
	public List<Organization> getParentOrganizations(@PathVariable("levelId") String positionLevelId);

	@Operation(summary = "设置所属组织")
	@PostMapping("/{levelId}/parentOrganizations")
	@ResponseBody
	public void setParentOrg(@PathVariable("levelId") String positionLevelId, @RequestBody List<String> orgs);

	@Operation(summary = "移除所属组织")
	@PutMapping("/{levelId}/parentOrganizations/delete")
	@ResponseBody
	public void removeParentOrg(@PathVariable("levelId") String positionLevelId, @RequestBody List<String> orgs);

	@Operation(summary = "创建职级")
	@PostMapping("")
	@ResponseBody
	public PositionLevel create(@RequestBody PositionLevel level);

	@Operation(summary = "更新职级")
	@PatchMapping("/{levelId}")
	@ResponseBody
	public PositionLevel update(@PathVariable("levelId") String positionLevelId, @RequestBody Map<String, Object> map);

//	@Operation(summary = "设置职级关系")
//	@PostMapping("/relation")
//	@ResponseBody
	public void addRelation(@RequestBody List<OrganizationPositionLevelRelation> organizationLevelRelations);

//	@Operation(summary = "移除职级关系")
//	@DeleteMapping("/relation")
//	@ResponseBody
	public void removeRelation(@RequestBody List<OrganizationPositionLevelRelationPK> ids);

	@Operation(summary = "删除职级")
	@PutMapping("/delete")
	@ResponseBody
	public void delete(@RequestBody String[] ids);

//	@Operation(summary = "获取拥有此岗位员工")
//	@GetMapping("/employees")
//	@ResponseBody
//	public List<Employee> getEmployees(OrganizationPositionLevelPK organizationLevelId);
}

package cn.sparrow.permission.mgt.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationGroup;
import cn.sparrow.permission.model.organization.OrganizationPositionLevel;
import cn.sparrow.permission.model.organization.OrganizationRole;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "组织服务")
@RequestMapping("/organizations")
public interface OrganizationService extends OrganizationRestService{

//	@Operation(summary = "获取下级组织")
//	@GetMapping("/{organizationId}/children")
	public List<Organization> getChildren(@PathVariable("organizationId") String organizationId);

//	@Operation(summary = "获取组织下的岗位")
//	@GetMapping("/{organizationId}/roles")
	public Page<OrganizationRole> getRoles(@PathVariable("organizationId") String organizationId, Pageable pageable);

//	@Operation(summary = "获取组织下的职级")
//	@GetMapping("/{organizationId}/levels")
	public Page<OrganizationPositionLevel> getLevels(@PathVariable("organizationId") String organizationId,
			Pageable pageable);

//	@Operation(summary = "获取组织下的群组")
//	@GetMapping("/{organizationId}/groups")
	public Page<OrganizationGroup> getGroups(@PathVariable("organizationId") String organizationId, Pageable pageable);

//	@Operation(summary = "获取组织下的员工")
//	@GetMapping("/{organizationId}/employees")
	public Page<Employee> getEmployees(@PathVariable("organizationId") String organizationId, Pageable pageable);

//	@Operation(summary = "设置组织关系")
//	@PostMapping("/{organizationId}/relations")
//	public void addRelations(@PathVariable("organizationId") String organizationId,
//			@RequestBody List<String> parentIds);
//
//	@Operation(summary = "移除组织关系")
//	@DeleteMapping("/{organizationId}/relations")
//	public void removeRelations(@PathVariable("organizationId") String organizationId,
//			@RequestBody List<String> parentIds);

	// @Operation(summary = "设置岗位所属组织")
	// @PostMapping("/updateRoleOrganization")
	// public void addRoles(@NotNull @RequestBody Set<OrganizationRolePK> ids);

	// @Operation(summary = "移除岗位所属组织")
	// @PostMapping("/removeRoleOrganization")
	// public void delRoles(@NotNull @RequestBody Set<OrganizationRolePK> ids);

	// @Operation(summary = "批量设置职级所属组织")
	// @PostMapping("/organizations/levels/batch")
	// public void addLevels(@NotNull @RequestBody Set<OrganizationPositionLevelPK>
	// ids);

	// @Operation(summary = "批量移除职级所属组织")
	// @DeleteMapping("/organizations/levels/batch")
	// public void delLevels(@NotNull @RequestBody Set<OrganizationPositionLevelPK>
	// ids);

	// @Operation(summary = "批量设置群组所属组织")
	// @PostMapping("/organizations/groups/batch")
	// public void addGroups(@NotNull @RequestBody Set<OrganizationGroupPK> ids);

	// @Operation(summary = "批量移除群组所属组织")
	// @DeleteMapping("/organizations/groups/batch")
	// public void delGroups(@NotNull @RequestBody Set<OrganizationGroupPK> ids);

}

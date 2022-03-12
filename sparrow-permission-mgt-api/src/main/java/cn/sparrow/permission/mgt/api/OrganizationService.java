package cn.sparrow.permission.mgt.api;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.constant.OrganizationChildTypeEnum;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationGroup;
import cn.sparrow.permission.model.organization.OrganizationPositionLevel;
import cn.sparrow.permission.model.organization.OrganizationRole;
import cn.sparrow.permission.model.resource.SparrowTree;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "组织服务")
@RequestMapping("/organizations")
public interface OrganizationService {

//	@Operation(summary = "获取下级组织")
//	@GetMapping("/{organizationId}/children")
	public List<Organization> getChildren(@PathVariable("organizationId") String organizationId);

	@Operation(summary = "获取下级")
	@GetMapping("/{organizationId}/children")
	@ResponseBody
	public List<?> getChildren(@PathVariable("organizationId") String organizationId,@NotNull OrganizationChildTypeEnum type);
	
	@Operation(summary = "获取上级组织")
	@GetMapping("/{organizationId}/parents")
	public List<Organization> getParents(@PathVariable("organizationId") String organizationId);

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

	@Operation(summary = "新增组织")
	@PostMapping("")
	public Organization create(@NotNull @RequestBody Organization organization);

	@Operation(summary = "更新组织")
	@PatchMapping("/{organizationId}")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Organization.class)))
	public Organization update(@PathVariable("organizationId") String organizationId,
			@RequestBody Map<String, Object> map);

	@Operation(summary = "删除组织")
	@DeleteMapping("")
	public void delete(@NotNull @RequestBody String[] ids);

//	@Operation(summary = "设置组织关系")
//	@PostMapping("/{organizationId}/relations")
//	public void addRelations(@PathVariable("organizationId") String organizationId,
//			@RequestBody List<String> parentIds);
//
//	@Operation(summary = "移除组织关系")
//	@DeleteMapping("/{organizationId}/relations")
//	public void removeRelations(@PathVariable("organizationId") String organizationId,
//			@RequestBody List<String> parentIds);

	@Operation(summary = "添加所属上级")
	@PostMapping("/{organizationId}/parents")
	public void addParent(@PathVariable("organizationId") String organizationId, @RequestBody List<String> parentIds);

	@Operation(summary = "移除所属上级")
	@DeleteMapping("/{organizationId}/parents")
	public void removeParent(@PathVariable("organizationId") String organizationId,
			@RequestBody List<String> parentIds);

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

	@Operation(summary = "获取组织树")
	@GetMapping("/tree")
	public SparrowTree<Organization, String> getTreeByParentId(@Nullable @RequestParam("parentId") String parentId);

	@Operation(summary = "组织详情")
	@GetMapping("/{organizationId}")
	public Organization get(@PathVariable("organizationId") String organizationId);
}

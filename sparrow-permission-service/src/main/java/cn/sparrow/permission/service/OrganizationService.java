package cn.sparrow.permission.service;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.sparrow.permission.model.SparrowTree;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationGroup;
import cn.sparrow.permission.model.organization.OrganizationGroupPK;
import cn.sparrow.permission.model.organization.OrganizationPositionLevel;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationRelation;
import cn.sparrow.permission.model.organization.OrganizationRelationPK;
import cn.sparrow.permission.model.organization.OrganizationRole;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/organizations")
public interface OrganizationService {
	
	@Operation(summary = "获取下级组织")
	@GetMapping("/organizations/getChildren")
	public List<Organization> getChildren(@NotNull @RequestParam("parentId") final String organizationId);

	@Operation(summary = "获取上级组织")
	@GetMapping("/organizations/getParents")
	public List<Organization> getParents(@NotNull @RequestParam("parentId") final String organizationId);

	@Operation(summary = "获取组织下的岗位")
	@GetMapping("/organizations/getRoles")
	public List<OrganizationRole> getRoles(@NotBlank @RequestParam("organizationId") final String organizationId);

	@Operation(summary = "获取组织下的职级")
	@GetMapping("/organizations/getLevels")
	public List<OrganizationPositionLevel> getLevels(
			@NotBlank @RequestParam("organizationId") final String organizationId);

	@Operation(summary = "获取组织下的群组")
	@GetMapping("/organizations/getGroups")
	public List<OrganizationGroup> getGroups(@NotBlank @RequestParam("organizationId") final String organizationId);

	@Operation(summary = "获取组织下的员工")
	@GetMapping("/organizations/getEmployees")
	public List<Employee> getEmployees(@NotBlank @RequestParam("organizationId") final String organizationId);

	@Operation(summary = "批量新增组织")
	@PostMapping("/organizations/batch")
	public void add(@NotNull @RequestBody List<Organization> organizations);

	@Operation(summary = "批量更新组织")
	@PatchMapping("/organizations/batch")
	public void update(@NotNull @RequestBody List<Organization> organizations);

	@Operation(summary = "批量删除组织")
	@DeleteMapping("/organizations/batch")
	public void del(@NotNull @RequestBody String[] ids);

	@Operation(summary = "设置组织关系")
	@PostMapping("/organizations/addRelation")
	public void addRelations(@NotNull @RequestBody Set<OrganizationRelation> organizationRelation);

	@Operation(summary = "移除组织关系")
	@DeleteMapping("/organizations/removeRelation")
	public void removeRelations(@NotNull @RequestBody Set<OrganizationRelationPK> ids);

	@Operation(summary = "更新所属上级")
	@DeleteMapping("/organizations/updateParent")
	public void updateParent(@NotNull @RequestBody Set<OrganizationRelation> organizationRelation);

	@Operation(summary = "批量设置岗位所属组织")
	@PostMapping("/organizations/roles/batch")
	public void addRoles(@NotNull @RequestBody Set<OrganizationRolePK> ids);

	@Operation(summary = "批量移除岗位所属组织")
	@DeleteMapping("/organizations/roles/batch")
	public void delRoles(@NotNull @RequestBody Set<OrganizationRolePK> ids);

	@Operation(summary = "批量设置职级所属组织")
	@PostMapping("/organizations/levels/batch")
	public void addLevels(@NotNull @RequestBody Set<OrganizationPositionLevelPK> ids);

	@Operation(summary = "批量移除职级所属组织")
	@DeleteMapping("/organizations/levels/batch")
	public void delLevels(@NotNull @RequestBody Set<OrganizationPositionLevelPK> ids);

	@Operation(summary = "批量设置群组所属组织")
	@PostMapping("/organizations/groups/batch")
	public void addGroups(@NotNull @RequestBody Set<OrganizationGroupPK> ids);

	@Operation(summary = "批量移除群组所属组织")
	@DeleteMapping("/organizations/groups/batch")
	public void delGroups(@NotNull @RequestBody Set<OrganizationGroupPK> ids);

	@Operation(summary = "根据父id获取组织树")
	@GetMapping("/orgranizations/getTreeByParentId")
	public SparrowTree<Organization, String> tree(@Null @RequestParam("parentId") String parentId);
}

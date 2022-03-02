package cn.sparrow.permission.service;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationRoleRelation;
import cn.sparrow.permission.model.organization.OrganizationRoleRelationPK;
import cn.sparrow.permission.model.organization.Role;
import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/roles")
public interface RoleService {
	
	@Operation(summary = "获取下属岗位")
	@GetMapping("/roles/getChildren")
	public List<OrganizationRoleRelation> getChildren(
			@NotNull @RequestParam("organizationId") final String organizationId,
			@NotNull @RequestParam("roleId") final String roleId);

	@Operation(summary = "获取上级岗位")
	@GetMapping("/roles/getParents")
	public List<OrganizationRoleRelation> getParents(
			@NotNull @RequestParam("organizationId") final String organizationId,
			@NotNull @RequestParam("roleId") final String roleId);

	@Operation(summary = "岗位所属的组织")
	@GetMapping("/roles/getParentOrganizations")
	public List<Organization> getParentOrganizations(@NotBlank @RequestParam("roleId") final String roleId);

	@Operation(summary = "批量新增岗位")
	@PostMapping("/roles/batch")
	public void add(@NotNull @RequestBody final List<Role> roles);

	@Operation(summary = "批量更新岗位")
	@PatchMapping("/roles/batch")
	public void update(@NotNull @RequestBody final List<Role> roles);

	@Operation(summary = "批量删除岗位")
	@DeleteMapping("/roles/batch")
	public void delete(@NotNull @RequestBody final String[] ids);

	@Operation(summary = "设置岗位关系")
	@PostMapping("/roles/relations")
	public void addRelations(@NotNull @RequestBody List<OrganizationRoleRelationPK> ids);

	@Operation(summary = "移除岗位关系")
	@DeleteMapping("/roles/relations")
	public void delRelations(@NotNull @RequestBody List<OrganizationRoleRelationPK> ids);
}

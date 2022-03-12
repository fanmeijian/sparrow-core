package cn.sparrow.permission.mgt.api;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import cn.sparrow.permission.model.organization.OrganizationRoleRelation;
import cn.sparrow.permission.model.organization.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "岗位服务")
@RequestMapping("/roles")
public interface RoleRestService {
	@Operation(summary = "岗位员工列表")
	@GetMapping("/{organizationRoleId}/employees")
	@ResponseBody
	public List<Employee> getEmployees(
			@Parameter(example = "organizationId_roleId", schema = @Schema(implementation = String.class)) @PathVariable("organizationRoleId") OrganizationRolePK organizationRoleId);

	@Operation(summary = "获取下属岗位")
	@GetMapping("/{organizationRoleId}/children")
	@ResponseBody
	public List<OrganizationRoleRelation> getChildren(
			@Parameter(example = "organizationId_roleId", schema = @Schema(implementation = String.class)) @PathVariable("organizationRoleId") OrganizationRolePK organizationRoleId);

	@Operation(summary = "获取上级岗位")
	@GetMapping("/{organizationRoleId}/parents")
	@ResponseBody
	public List<OrganizationRoleRelation> getParents(
			@Parameter(example = "organizationId_roleId", schema = @Schema(implementation = String.class)) @PathVariable("organizationRoleId") OrganizationRolePK organizationRoleId);

	@Operation(summary = "设置上级岗位")
	@PostMapping("/{organizationRoleId}/parents")
	@ResponseBody
	public void addParents(
			@Parameter(example = "organizationId_roleId", schema = @Schema(implementation = String.class)) @PathVariable("organizationRoleId") OrganizationRolePK organizationRoleId,
			@NotNull @RequestBody List<OrganizationRolePK> ids);

	@Operation(summary = "移除上级岗位")
	@DeleteMapping("/{organizationRoleId}/parents")
	@ResponseBody
	public void delParents(
			@Parameter(example = "organizationId_roleId", schema = @Schema(implementation = String.class)) @PathVariable("organizationRoleId") OrganizationRolePK organizationRoleId,
			@NotNull @RequestBody List<OrganizationRolePK> ids);

	@Operation(summary = "岗位所属的组织")
	@GetMapping("/{roleId}/parentOrganizations")
	@ResponseBody
	public List<Organization> getParentOrganizations(@PathVariable("roleId") String roleId);

	@Operation(summary = "岗位详情")
	@GetMapping("/{roleId}")
	@ResponseBody
	public Role get(@PathVariable("roleId") String roleId);
}

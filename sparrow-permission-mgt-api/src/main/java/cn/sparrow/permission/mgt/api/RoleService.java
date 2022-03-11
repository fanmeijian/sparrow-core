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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationRoleRelation;
import cn.sparrow.permission.model.organization.OrganizationRoleRelationPK;
import cn.sparrow.permission.model.organization.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "岗位服务")
@RequestMapping("/roles")
public interface RoleService {
	
	@Operation(summary = "获取下属岗位")
	@GetMapping("/children")
	@ResponseBody
	public List<OrganizationRoleRelation> getChildren(String organizationId, String roleId);

	@Operation(summary = "获取上级岗位")
	@GetMapping("/parents")
	@ResponseBody
	public List<OrganizationRoleRelation> getParents(String organizationId,String roleId);

	@Operation(summary = "岗位所属的组织")
	@GetMapping("/parentOrganizations")
	@ResponseBody
	public List<Organization> getParentOrganizations(String roleId);

	@Operation(summary = "设置岗位所属组织")
	@PostMapping("/{roleId}/parentOrganizations")
	@ResponseBody
	public void setParentOrg(@PathVariable("roleId") String roleId, @RequestBody List<String> orgs);

	@Operation(summary = "移除岗位所属组织")
	@DeleteMapping("/{roleId}/parentOrganizations")
	@ResponseBody
	public void removeParentOrg(@PathVariable("roleId") String roleId, @RequestBody List<String> orgs);

	@Operation(summary = "岗位列表")
	@GetMapping("")
	@ResponseBody
	public Page<Role> all(@Nullable Pageable pageable,@Nullable Role role);

	@Operation(summary = "新增岗位")
	@PostMapping("")
	@ResponseBody
	public Role create(@NotNull @RequestBody Role role);

	@Operation(summary = "更新岗位")
	@PatchMapping("/{roleId}")
	@ResponseBody
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Role.class)))
	public Role update(@PathVariable("roleId") String roleId,@RequestBody Map<String,Object> map);

	@Operation(summary = "删除岗位")
	@DeleteMapping("")
	public void delete(@NotNull @RequestBody final String[] ids);

	@Operation(summary = "设置岗位关系")
	@PostMapping("/relation")
	public void addRelations(@NotNull @RequestBody List<OrganizationRoleRelationPK> ids);

	@Operation(summary = "移除岗位关系")
	@DeleteMapping("/relation")
	public void delRelations(@NotNull @RequestBody List<OrganizationRoleRelationPK> ids);

	@Operation(summary = "岗位员工列表")
	@GetMapping("/{organizationId}/{roleId}/employees")
	public List<Employee> getEmployees(@PathVariable("organizationId") String organizationId, @PathVariable("roleId") String roleId);
}

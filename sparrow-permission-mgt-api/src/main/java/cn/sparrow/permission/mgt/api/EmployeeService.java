package cn.sparrow.permission.mgt.api;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.EmployeeOrganizationLevel;
import cn.sparrow.permission.model.organization.EmployeeOrganizationRole;
import cn.sparrow.permission.model.organization.EmployeeRelation;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import cn.sparrow.permission.model.resource.SparrowTree;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "employee", description = "人员服务")
@RequestMapping("/employees")
public interface EmployeeService {

	@Operation(summary = "新增员工")
	@PostMapping("")
	@ResponseBody
	public Employee create(@RequestBody Employee employee);
	
	@Operation(summary = "员工详情")
	@GetMapping("/{employeeId}")
	@ResponseBody
	public Employee get(@PathVariable("employeeId") String employeeId);
	
	@Operation(summary = "员工详情")
	@GetMapping("/all")
	@ResponseBody
	public Page<Employee> all(@Nullable Pageable pageable,@Nullable Employee employee);

	@Operation(summary = "更新员工")
	@PatchMapping("/{employeeId}")
	@ResponseBody
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Employee.class)))
	public Employee update(@PathVariable("employeeId") String employeeId,@RequestBody Map<String, Object> map);

	@Operation(summary = "删除员工")
	@PutMapping("/delete")
	@ResponseBody
	public void delete(@RequestBody String[] ids);

	@Operation(summary = "获取直接下属")
	@GetMapping("/{employeeId}/children")
	@ResponseBody
	public List<EmployeeRelation> getChildren(@PathVariable("employeeId") String employeeId);

	@Operation(summary = "获取直接上级")
	@GetMapping("/{employeeId}/parents")
	@ResponseBody
	public List<EmployeeRelation> getParents(@PathVariable("employeeId") String employeeId);

	@Operation(summary = "获取所属职级")
	@GetMapping("/{employeeId}/positionLevels")
	@ResponseBody
	public List<EmployeeOrganizationLevel> getLevels(@PathVariable("employeeId") String employeeId);

	@Operation(summary = "获取担任岗位")
	@GetMapping("/{employeeId}/roles")
	@ResponseBody
	public List<EmployeeOrganizationRole> getRoles(@PathVariable("employeeId") String employeeId);

	@Operation(summary = "获取员工关系树")
	@GetMapping("/{employeeId}/tree")
	@ResponseBody
	public SparrowTree<Employee, String> tree(@PathVariable("employeeId") String employeeId);

	@Operation(summary = "设置担任岗位")
	@PostMapping("/{employeeId}/roles")
	@ResponseBody
	public void addRole(@PathVariable("employeeId") String employeeId, @RequestBody List<OrganizationRolePK> organizationRoleIds);

	@Operation(summary = "移除担任岗位")
	@PutMapping("/{employeeId}/roles/delete")
	@ResponseBody
	public void removeRole(@PathVariable("employeeId") String employeeId,
			@RequestBody List<OrganizationRolePK> organizationRoleIds);

	@Operation(summary = "设置所属职级别")
	@PostMapping("/{employeeId}/positionLevels")
	@ResponseBody
	public void addLevel(@PathVariable("employeeId") String employeeId,
			@RequestBody List<OrganizationPositionLevelPK> organizationPositionLevelIds);

	@Operation(summary = "移除所属职级别")
	@PutMapping("/{employeeId}/positionLevels/delete")
	@ResponseBody
	public void removeLevel(@PathVariable("employeeId") String employeeId,
			@RequestBody List<OrganizationPositionLevelPK> organizationPositionLevelIds);

	@Operation(summary = "设置员工上级")
	@PostMapping("/{employeeId}/relation")
	@ResponseBody
	public void addParent(@PathVariable("employeeId") String employeeId, @RequestBody List<String> parentIds);

	@Operation(summary = "移除员工上级")
	@PutMapping("/{employeeId}/relation/delete")
	@ResponseBody
	public void removeParent(@PathVariable("employeeId") String employeeId, @RequestBody List<String> parentIds);

	public long getChildCount(String employeeId);

}

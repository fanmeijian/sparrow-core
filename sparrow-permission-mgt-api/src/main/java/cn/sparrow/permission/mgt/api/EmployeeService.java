package cn.sparrow.permission.mgt.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.Null;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.istack.NotNull;

import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.EmployeeOrganizationLevel;
import cn.sparrow.permission.model.organization.EmployeeOrganizationLevelPK;
import cn.sparrow.permission.model.organization.EmployeeOrganizationRole;
import cn.sparrow.permission.model.organization.EmployeeOrganizationRolePK;
import cn.sparrow.permission.model.organization.EmployeeRelation;
import cn.sparrow.permission.model.organization.EmployeeRelationPK;
import cn.sparrow.permission.model.organization.PositionLevel;
import cn.sparrow.permission.model.organization.Role;
import cn.sparrow.permission.model.resource.SparrowTree;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "人员服务")
@RequestMapping("/employees")
public interface EmployeeService {
	
	@Operation(summary = "新增员工")
	@PostMapping("")
	public Employee create(Employee employee);

	@Operation(summary = "更新员工")
	@PatchMapping("/{employeeId}")
	@ResponseBody
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Employee.class)))
	public Employee update(@PathVariable("employeeId") String employeeId,Map<String, Object> map);

	@Operation(summary = "删除员工")
	@DeleteMapping("")
	@ResponseBody
	public void delete(@RequestBody String[] ids);

	@Operation(summary = "获取直接下属")
	@GetMapping("/{employeeId}/children")
	@ResponseBody
	public List<Employee> getChildren(@PathVariable("employeeId") String employeeId);

	@Operation(summary = "获取直接上级")
	@GetMapping("/{employeeId}/parents")
	@ResponseBody
	public List<Employee> getParents(@PathVariable("employeeId") String employeeId);

	@Operation(summary = "获取所属职级")
	@GetMapping("/{employeeId}/positionLevels")
	@ResponseBody
	public List<PositionLevel> getLevels(@PathVariable("employeeId") String employeeId);

	@Operation(summary = "获取担任岗位")
	@GetMapping("/{employeeId}/roles")
	@ResponseBody
	public List<Role> getRoles(@PathVariable("employeeId") String employeeId);

	@Operation(summary = "获取员工关系树")
	@GetMapping("/{employeeId}/tree")
	@ResponseBody
	public SparrowTree<Employee, String> tree(@PathVariable("employeeId") String employeeId);

	@Operation(summary = "设置担任岗位")
	@PostMapping("/roles")
	@ResponseBody
	public void addRole(@RequestBody Set<EmployeeOrganizationRolePK> ids);

	@Operation(summary = "移除担任岗位")
	@DeleteMapping("/roles")
	@ResponseBody
	public void removeRole( @RequestBody Set<EmployeeOrganizationRolePK> ids);

	@Operation(summary = "设置所属职级别")
	@PostMapping("/positionLevels")
	@ResponseBody
	public void addLevel(@RequestBody List<EmployeeOrganizationLevelPK> ids);

	@Operation(summary = "移除所属职级别")
	@DeleteMapping("/positionLevels")
	@ResponseBody
	public void removeLevel( @RequestBody Set<EmployeeOrganizationLevelPK> ids);

	@Operation(summary = "设置员工关系")
	@PostMapping("/relation")
	@ResponseBody
	public void addRelation(@RequestBody Set<EmployeeRelationPK> ids);

	@Operation(summary = "移除员工关系")
	@DeleteMapping("/relation")
	@ResponseBody
	public void removeRelation( @RequestBody Set<EmployeeRelationPK> ids);
	
	public long getChildCount(String employeeId);
}

package cn.sparrow.permission.mgt.api;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Null;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.istack.NotNull;

import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.EmployeeOrganizationLevel;
import cn.sparrow.permission.model.organization.EmployeeOrganizationLevelPK;
import cn.sparrow.permission.model.organization.EmployeeOrganizationRole;
import cn.sparrow.permission.model.organization.EmployeeOrganizationRolePK;
import cn.sparrow.permission.model.organization.EmployeeRelation;
import cn.sparrow.permission.model.organization.EmployeeRelationPK;
import cn.sparrow.permission.model.resource.SparrowTree;
import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/employees")
public interface EmployeeService {
	
	@Operation(summary = "设置主组织")
	@PutMapping("/employees/setMasterOrg")
	public void updateOrganization(@RequestParam("employeeId") String employeeId,
			@RequestParam("organizationId") String organizationId);

	@Operation(summary = "获取直接下属")
	@GetMapping("/employees/getChildren")
	public List<EmployeeRelation> getChildren(@NotNull @RequestParam("parentId") String parentId);

	@Operation(summary = "获取直接上级")
	@GetMapping("/employees/getParents")
	public List<EmployeeRelation> getParents(@NotNull @RequestParam("employeeId") String employeeId);

	@Operation(summary = "获取所属职级")
	@GetMapping("/employees/getLevels")
	public List<EmployeeOrganizationLevel> getLevels(@NotNull @RequestParam("employeeId") String employeeId);

	@Operation(summary = "获取担任岗位")
	@GetMapping("/employees/getRoles")
	public List<EmployeeOrganizationRole> getRoles(@NotNull @RequestParam("employeeId") String employeeId);

	@Operation(summary = "获取员工关系树")
	@GetMapping("/employees/getTreeByParentId")
	public SparrowTree<Employee, String> tree(@Null @RequestParam("parentId") String parentId);

	@Operation(summary = "批量新增员工")
	@PostMapping("/employees/batch")
	public void add(@NotNull @RequestBody List<Employee> employees);

	@Operation(summary = "批量更新员工")
	@PatchMapping("/employees/batch")
	public void update(@NotNull @RequestBody List<Employee> employees);

	@Operation(summary = "批量删除员工")
	@DeleteMapping("/employees/batch")
	public void delete(@NotNull @RequestBody String[] ids);

	@Operation(summary = "批量设置担任岗位")
	@PostMapping("/employees/roles/batch")
	public void addRoles(@NotNull @RequestBody Set<EmployeeOrganizationRole> ids);

	@Operation(summary = "批量移除担任岗位")
	@DeleteMapping("/employees/roles/batch")
	public void delRoles(@NotNull @RequestBody Set<EmployeeOrganizationRolePK> ids);

	@Operation(summary = "批量设置所属职级别")
	@PostMapping("/employees/levels/batch")
	public void addLevels(@NotNull @RequestBody List<EmployeeOrganizationLevel> ids);

	@Operation(summary = "批量移除所属职级别")
	@DeleteMapping("/employees/levels/batch")
	public void delLevels(@NotNull @RequestBody Set<EmployeeOrganizationLevelPK> ids);

	@Operation(summary = "设置员工关系")
	@PostMapping("/employees/relations/batch")
	public void addRelations(@NotNull @RequestBody Set<EmployeeRelation> ids);

	@Operation(summary = "移除员工关系")
	@DeleteMapping("/employees/relations/batch")
	public void delRelations(@NotNull @RequestBody Set<EmployeeRelationPK> ids);
	
	public long getChildCount(String parentId);
}

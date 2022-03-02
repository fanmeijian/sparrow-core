package cn.sparrow.permission.service;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.istack.NotNull;

import cn.sparrow.permission.model.organization.EmployeeOrganizationLevel;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelation;
import cn.sparrow.permission.model.organization.PositionLevel;
import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/positionLevels")
public interface PositionLevelService {
	
	@Operation(summary = "获取下属职级")
	@GetMapping("/positionLevels/getChildren")
	public List<OrganizationPositionLevelRelation> getChildren(
			@NotNull @RequestParam("organizationId") final String organizationId,
			@NotNull @RequestParam("positionLevelId") final String positionLevelId);

	@Operation(summary = "获取上级职级")
	@GetMapping("/positionLevels/getParents")
	public List<OrganizationPositionLevelRelation> getParents(
			@NotNull @RequestParam("organizationId") final String organizationId,
			@NotNull @RequestParam("positionLevelId") final String positionLevelId);

	@Operation(summary = "获取所属组织")
	@GetMapping("/positionLevels/getParentOrganizations")
	public List<Organization> getParentOrganizations(
			@NotBlank @RequestParam("positionLevelId") final String positionLevelId);

	@Operation(summary = "批量创建职级")
	@PostMapping("/positionLevels/batch")
	public void add(@NotNull @RequestBody final List<PositionLevel> levels);

	@Operation(summary = "批量更新职级")
	@PatchMapping("/positionLevels/batch")
	public void update(@NotNull @RequestBody final List<PositionLevel> levels);

	@Operation(summary = "批量设置职级关系")
	@PostMapping("/positionLevels/relations/batch")
	public void addRelations(@NotNull @RequestBody List<OrganizationPositionLevelRelation> organizationLevelRelations);

	public void delBatch(String[] ids);
	
	public List<EmployeeOrganizationLevel> getEmployees(OrganizationPositionLevelPK organizationLevelId);
	
	public PositionLevel save(PositionLevel lvel);
}

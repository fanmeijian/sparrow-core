package cn.sparrow.permission.mgt.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.OrganizationPositionLevel;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.PositionLevel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "职级服务")
@RequestMapping("/jobLevels")
public interface JobLevelRestService {
	@Operation(summary = "职级详情")
	@GetMapping("/{levelId}")
	@ResponseBody
	public PositionLevel get(@PathVariable("levelId") String positionLevelId);
	
	@Operation(summary = "获取下级")
	@GetMapping("/{organizationLevelId}/children")
	@ResponseBody
	public List<OrganizationPositionLevel> getChildren(
			@Parameter(example = "organizationId_levelId", schema = @Schema(implementation = String.class)) @PathVariable("organizationLevelId") OrganizationPositionLevelPK organizationLevelId);

	@Operation(summary = "获取上级")
	@GetMapping("/{organizationLevelId}/parents")
	@ResponseBody
	public List<OrganizationPositionLevel> getParents(
			@Parameter(example = "organizationId_levelId", schema = @Schema(implementation = String.class)) @PathVariable("organizationLevelId") OrganizationPositionLevelPK organizationLevelId);

	@Operation(summary = "设置上级")
	@PostMapping("/{organizationLevelId}/parents")
	@ResponseBody
	public void addRelation(
			@Parameter(example = "organizationId_levelId", schema = @Schema(implementation = String.class)) @PathVariable("organizationLevelId") OrganizationPositionLevelPK organizationLevelId,
			@RequestBody List<OrganizationPositionLevelPK> ids);

	@Operation(summary = "移除上级")
	@PutMapping("/{organizationLevelId}/parents/delete")
	@ResponseBody
	public void removeRelation(
			@Parameter(example = "organizationId_levelId", schema = @Schema(implementation = String.class)) @PathVariable("organizationLevelId") OrganizationPositionLevelPK organizationLevelId,
			@RequestBody List<OrganizationPositionLevelPK> ids);

	@Operation(summary = "获取拥有此级别员工")
	@GetMapping("/{organizationLevelId}/employees")
	@ResponseBody
	public List<Employee> getEmployees(
			@Parameter(example = "organizationId_levelId", schema = @Schema(implementation = String.class)) @PathVariable("organizationLevelId") OrganizationPositionLevelPK organizationLevelId);
}

package cn.sparrow.permission.mgt.api;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.constant.GroupTypeEnum;
import cn.sparrow.permission.model.group.Group;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.Organization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;

@Tag(name = "群组服务")
@RequestMapping("/groups")
public interface GroupService extends GroupRestService{

	@Operation(summary = "新增群组")
	@PostMapping("")
	@ResponseBody
	public Group create(@RequestBody Group group);
	
	@Operation(summary = "更新群组")
	@PatchMapping("/{groupId}")
	@ResponseBody
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Group.class)))
	public Group update(@PathVariable("groupId") String groupId, Map<String, Object> map);

	@Operation(summary = "删除群组")
	@DeleteMapping("")
	@ResponseBody
	public void delete(@RequestBody List<String> ids);

//	@Operation(summary = "递归获取群组成员，最终为员工列表")
//	@GetMapping("/allEmployees")
//	@ResponseBody
	public List<Employee> getFinalEmployees(String groupId);

	@Operation(summary = "获取所属组织")
	@GetMapping("/{groupId}/parentOrganizations")
	@ResponseBody
	public List<Organization> getParentOrgs(@PathVariable("groupId") String groupId);

	@Operation(summary = "设置所属组织")
	@PostMapping("/{groupId}/parentOrganizations")
	@ResponseBody
	public void setParentOrgs(@PathVariable("groupId") String groupId, @RequestBody List<String> orgs);

	@Operation(summary = "移除所属组织")
	@DeleteMapping("/{groupId}/parentOrganizations")
	@ResponseBody
	public void removeParentOrgs(@PathVariable("groupId") String groupId, @RequestBody List<String> orgs);

	@Operation(summary = "添加组成员")
	@PostMapping("/{groupId}/members")
	@ResponseBody
	public void addMembers(@PathVariable("groupId") String groupId, @NotNull GroupTypeEnum type, @RequestBody List<Object> memberIds);
	
	@Operation(summary = "移除组成员")
	@DeleteMapping("/{groupId}/members")
	@ResponseBody
	public void removeMembers(@PathVariable("groupId") String groupId, @NotNull GroupTypeEnum type, @RequestBody List<Object> memberIds);
	
}

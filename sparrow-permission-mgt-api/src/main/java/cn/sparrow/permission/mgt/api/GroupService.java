package cn.sparrow.permission.mgt.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.istack.NotNull;

import cn.sparrow.permission.model.group.Group;
import cn.sparrow.permission.model.group.GroupMember;
import cn.sparrow.permission.model.group.GroupOrganizationPK;
import cn.sparrow.permission.model.group.GroupPositionLevelPK;
import cn.sparrow.permission.model.group.GroupRelation;
import cn.sparrow.permission.model.group.GroupRelationPK;
import cn.sparrow.permission.model.group.GroupRolePK;
import cn.sparrow.permission.model.group.GroupSysrolePK;
import cn.sparrow.permission.model.group.GroupUserPK;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.resource.SparrowTree;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "群组服务")
@RequestMapping("/groups")
public interface GroupService {

	@Operation(summary = "新增群组")
	@PostMapping("")
	@ResponseBody
	public Group add(@RequestBody Group group);

	@Operation(summary = "群组列表")
	@GetMapping("")
	@ResponseBody
	public Page<Group> all(@Nullable Pageable pageable,@Nullable Group group);

	@Operation(summary = "更新群组")
	@PatchMapping("/{groupId}")
	@ResponseBody
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Group.class)))
	public Group update(@PathVariable("groupId") String groupId, Map<String, Object> map);

	@Operation(summary = "删除群组")
	@DeleteMapping("")
	public void delete(@RequestBody List<String> ids);
	
	@Operation(summary = "设置群组成员")
	@PostMapping("/members")
	@ResponseBody
	public void addMembers(@RequestBody GroupMember groupMember);

	@Operation(summary = "移除群组成员")
	@DeleteMapping("/members")
	@ResponseBody
	public void removeMembers(@RequestBody GroupMember groupMember);

	@Operation(summary = "获取群组成员")
	@GetMapping("/members")
	@ResponseBody
	public GroupMember getGroupMember(String groupId);

	@Operation(summary = "递归获取群组成员，最终为员工列表")
	@GetMapping("/allEmployees")
	@ResponseBody
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

	// @Operation(summary = "添加子群组")
	// @PostMapping("/relations")
	// @ResponseBody
	// public void addRelations(@RequestBody Set<GroupRelationPK> ids);

	// @Operation(summary = "移除子群组")
	// @DeleteMapping("/relations")
	// @ResponseBody
	// public void delRelations(@RequestBody Set<GroupRelationPK> ids);

	// @Operation(summary = "添加组织成员")
	// @PostMapping("/organizations")
	// @ResponseBody
	// public void addOrganizations(@RequestBody Set<GroupOrganizationPK> ids);

	// @Operation(summary = "移除组织成员")
	// @DeleteMapping("/organizations")
	// @ResponseBody
	// public void delOrganizations(@RequestBody Set<GroupOrganizationPK> ids);

	// @Operation(summary = "添加岗位成员")
	// @PostMapping("/roles")
	// @ResponseBody
	// public void addRoles(@RequestBody Set<GroupRolePK> ids);

	// @Operation(summary = "移除岗位成员")
	// @DeleteMapping("/roles")
	// @ResponseBody
	// public void delRoles(@RequestBody Set<GroupRolePK> ids);

	// @Operation(summary = "添加职级成员")
	// @PostMapping("/levels")
	// @ResponseBody
	// public void addLevels(@RequestBody Set<GroupPositionLevelPK> ids);

	// @Operation(summary = "移除职级成员")
	// @DeleteMapping("/levels")
	// @ResponseBody
	// public void delLevels(@RequestBody Set<GroupPositionLevelPK> ids);

	// @Operation(summary = "添加角色成员")
	// @PostMapping("/sysroles")
	// @ResponseBody
	// public void addSysroles(@RequestBody Set<GroupSysrolePK> ids);

	// @Operation(summary = "移除角色成员")
	// @DeleteMapping("/sysroles")
	// @ResponseBody
	// public void delSysroles(@RequestBody Set<GroupSysrolePK> ids);

	// @Operation(summary = "添加用户成员")
	// @PostMapping("/users")
	// @ResponseBody
	// public void addUsers(@RequestBody Set<GroupUserPK> ids);

	// @Operation(summary = "移除用户成员")
	// @DeleteMapping("/users")
	// @ResponseBody
	// public void delUsers(@RequestBody Set<GroupUserPK> ids);

	@Operation(summary = "获取组织树")
	@GetMapping("/getTreeByParentId")
	@ResponseBody
	public SparrowTree<Group, String> getTree(String groupId);
}

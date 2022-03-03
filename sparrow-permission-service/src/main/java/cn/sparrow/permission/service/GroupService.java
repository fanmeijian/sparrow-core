package cn.sparrow.permission.service;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.istack.NotNull;

import cn.sparrow.permission.model.SparrowTree;
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
import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("/groups")
public interface GroupService {
	
	@Operation(summary = "设置群组成员")
	@PostMapping("/groups/addMembers")
	public void addMembers(@NotNull @RequestBody GroupMember groupMember);

	@Operation(summary = "获取群组成员")
	@GetMapping("/groups/getGroupMember")
	public GroupMember getGroupMember(@NotBlank @RequestParam("groupId") final String groupId);

	@Operation(summary = "递归获取群组成员")
	@GetMapping("/groups/getFinalEmployees")
	public List<Employee> getFinalEmployees(@NotBlank @RequestParam("groupId") final String groupId);

	@Operation(summary = "批量新增群组")
	@PostMapping("/groups/batch")
	public void add(@NotNull @RequestBody final List<Group> groups);

	@Operation(summary = "获取群组列表")
	@PostMapping("/groups")
	public Group save(@NotNull @RequestBody final Group group);

	@Operation(summary = "批量更新群组")
	@PatchMapping("/groups/batch")
	public void update(@NotNull @RequestBody final List<Group> groups);

	@Operation(summary = "批量删除群组")
	@DeleteMapping("/groups/batch")
	public void delete(@NotNull @RequestBody final String[] ids);

	@Operation(summary = "添加子群组")
	@PostMapping("/groups/relations/batch")
	public void addRelations(@NotNull @RequestBody Set<GroupRelation> ids);

	@Operation(summary = "移除子群组")
	@DeleteMapping("/groups/relations/batch")
	public void delRelations(@NotNull @RequestBody Set<GroupRelationPK> ids);

	@Operation(summary = "添加组织成员")
	@PostMapping("/groups/organizations/batch")
	public void addOrganizations(@NotNull @RequestBody Set<GroupOrganizationPK> ids);

	@Operation(summary = "移除组织成员")
	@DeleteMapping("/groups/organizations/batch")
	public void delOrganizations(@NotNull @RequestBody Set<GroupOrganizationPK> ids);

	@Operation(summary = "添加岗位成员")
	@PostMapping("/groups/roles/batch")
	public void addRoles(@NotNull @RequestBody Set<GroupRolePK> ids);

	@Operation(summary = "移除岗位成员")
	@DeleteMapping("/groups/roles/batch")
	public void delRoles(@NotNull @RequestBody Set<GroupRolePK> ids);

	@Operation(summary = "添加职级成员")
	@PostMapping("/groups/levels/batch")
	public void addLevels(@NotNull @RequestBody Set<GroupPositionLevelPK> ids);

	@Operation(summary = "移除职级成员")
	@DeleteMapping("/groups/levels/batch")
	public void delLevels(@NotNull @RequestBody Set<GroupPositionLevelPK> ids);

	@Operation(summary = "添加角色成员")
	@PostMapping("/groups/sysroles/batch")
	public void addSysroles(@NotNull @RequestBody Set<GroupSysrolePK> ids);

	@Operation(summary = "移除角色成员")
	@DeleteMapping("/groups/sysroles/batch")
	public void delSysroles(@NotNull @RequestBody Set<GroupSysrolePK> ids);

	@Operation(summary = "添加用户成员")
	@PostMapping("/groups/users/batch")
	public void addUsers(@NotNull @RequestBody Set<GroupUserPK> ids);

	@Operation(summary = "移除用户成员")
	@DeleteMapping("/groups/users/batch")
	public void delUsers(@NotNull @RequestBody Set<GroupUserPK> ids);

	@Operation(summary = "获取组织树")
	@GetMapping("/groups/getTreeByParentId")
	public SparrowTree<Group, String> tree(@Null @RequestParam("parentId") String parentId);
}

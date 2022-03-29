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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.constant.GroupTypeEnum;
import cn.sparrow.permission.model.group.Group;
import cn.sparrow.permission.model.organization.OrganizationGroup;
import cn.sparrow.permission.model.resource.SparrowTree;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;

@Tag(name = "group", description = "群组服务")
@RequestMapping("/groups")
public interface GroupRestService {
	
	@Operation(summary = "群组详情")
	@GetMapping("/{groupId}")
	@ResponseBody
	public Group get(@PathVariable("groupId") String groupId);
	
	@Operation(summary = "群组树")
	@GetMapping("/tree")
	@ResponseBody
	public SparrowTree<Group, String> getTree(String parentId);

	@Operation(summary = "群组列表")
	@GetMapping("")
	@ResponseBody
	public Page<Group> all(@Nullable Pageable pageable, @Nullable Group group);
	
	@Operation(summary = "组成员列表")
	@GetMapping("/{groupId}/members")
	@ResponseBody
	public Page<?> getMembers(@PathVariable("groupId") String groupId,GroupTypeEnum type ,@Nullable Pageable pageable);

	@Operation(summary = "新增群组")
	@PostMapping("")
	@ResponseBody
	public Group create(@RequestBody Group group);
	
	@Operation(summary = "更新群组")
	@PatchMapping("/{groupId}")
	@ResponseBody
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Group.class)))
	public Group update(@PathVariable("groupId") String groupId,@RequestBody Map<String, Object> map);

	@Operation(summary = "删除群组")
	@PutMapping("/delete")
	@ResponseBody
	public void delete(@RequestBody List<String> ids);

	@Operation(summary = "获取所属组织")
	@GetMapping("/{groupId}/parentOrganizations")
	@ResponseBody
	public Page<OrganizationGroup> getParentOrgs(@PathVariable("groupId") String groupId, @Nullable Pageable pageable);

	@Operation(summary = "设置所属组织")
	@PostMapping("/{groupId}/parentOrganizations")
	@ResponseBody
	public void setParentOrgs(@PathVariable("groupId") String groupId, @RequestBody List<String> orgs);

	@Operation(summary = "移除所属组织")
	@PutMapping("/{groupId}/parentOrganizations/delete")
	@ResponseBody
	public void removeParentOrgs(@PathVariable("groupId") String groupId, @RequestBody List<String> orgs);

	@Operation(summary = "添加组成员")
	@PostMapping("/{groupId}/members")
	@ResponseBody
	public void addMembers(@PathVariable("groupId") String groupId, @RequestParam("type") GroupTypeEnum type, @RequestBody List<Object> memberIds);
	
	@Operation(summary = "移除组成员")
	@PutMapping("/{groupId}/members/delete")
	@ResponseBody
	public void removeMembers(@PathVariable("groupId") String groupId, @RequestParam("type") GroupTypeEnum type, @RequestBody List<Object> memberIds);

}

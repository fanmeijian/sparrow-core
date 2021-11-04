package cn.sparrow.common.controller;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sun.istack.NotNull;
import cn.sparrow.common.service.GroupService;
import cn.sparrow.group.repository.GroupRepository;
import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.group.Group;
import cn.sparrow.model.group.GroupPositionLevelPK;
import cn.sparrow.model.group.GroupMember;
import cn.sparrow.model.group.GroupOrganizationPK;
import cn.sparrow.model.group.GroupRelation;
import cn.sparrow.model.group.GroupRelationPK;
import cn.sparrow.model.group.GroupRolePK;
import cn.sparrow.model.group.GroupSysrolePK;
import cn.sparrow.model.group.GroupUserPK;
import cn.sparrow.model.organization.Employee;

@RestController
public class GroupController {
  @Autowired GroupService groupService;
  @Autowired GroupRepository groupRepository;
  
  @PostMapping("/groups/addMembers")
  public void addMembers(@NotNull @RequestBody GroupMember groupMember) {
    groupService.saveGroupMember(groupMember);
  }
  
  @GetMapping("/groups/getGroupMember")
  public GroupMember getGroupMember(@NotBlank @RequestParam("groupId") final String groupId) {
	  return groupService.getGroupMember(groupId);
  }
  
  @GetMapping("/groups/getFinalEmployees")
  public List<Employee> getFinalEmployees(@NotBlank @RequestParam("groupId") final String groupId) {
	  return groupService.getFinalEmployees(groupId);
  }
  
  @PostMapping("/groups/batch")
  public void add(@NotNull @RequestBody final List<Group> groups) {
    groupRepository.saveAll(groups);
  }
  
  @PostMapping("/groups")
  public Group save(@NotNull @RequestBody final Group group) {
	 return groupService.save(group);
  }

  @PatchMapping("/groups/batch")
  public void update(@NotNull @RequestBody final List<Group> groups) {
    groupRepository.saveAll(groups);
  }

  @DeleteMapping("/groups/batch")
  public void delete(@NotNull @RequestBody final String[] ids) {
    groupRepository.deleteByIdIn(ids);
  }
  

  
  @PostMapping("/groups/relations/batch")
  public void addRelations(@NotNull @RequestBody Set<GroupRelation> ids) {
    groupService.addRelations(ids);
  }
  
  @DeleteMapping("/groups/relations/batch")
  public void delRelations(@NotNull @RequestBody Set<GroupRelationPK> ids) {
    groupService.delRelations(ids);
  }
  
  @PostMapping("/groups/organizations/batch")
  public void addOrganizations(@NotNull @RequestBody Set<GroupOrganizationPK> ids) {
    groupService.addOrganizations(ids);
  }
  
  @DeleteMapping("/groups/organizations/batch")
  public void delOrganizations(@NotNull @RequestBody Set<GroupOrganizationPK> ids) {
    groupService.delOrganizations(ids);
  }
  
  @PostMapping("/groups/roles/batch")
  public void addRoles(@NotNull @RequestBody Set<GroupRolePK> ids) {
    groupService.addRoles(ids);
  }
  
  @DeleteMapping("/groups/roles/batch")
  public void delRoles(@NotNull @RequestBody Set<GroupRolePK> ids) {
    groupService.delRoles(ids);
  }
  
  @PostMapping("/groups/levels/batch")
  public void addLevels(@NotNull @RequestBody Set<GroupPositionLevelPK> ids) {
    groupService.addLevels(ids);
  }
  
  @DeleteMapping("/groups/levels/batch")
  public void delLevels(@NotNull @RequestBody Set<GroupPositionLevelPK> ids) {
    groupService.delLevels(ids);
  }
  
  @PostMapping("/groups/sysroles/batch")
  public void addSysroles(@NotNull @RequestBody Set<GroupSysrolePK> ids) {
    groupService.addSysroles(ids);
  }
  
  @DeleteMapping("/groups/sysroles/batch")
  public void delSysroles(@NotNull @RequestBody Set<GroupSysrolePK> ids) {
    groupService.delSysroles(ids);
  }
  
  @PostMapping("/groups/users/batch")
  public void addUsers(@NotNull @RequestBody Set<GroupUserPK> ids) {
    groupService.addUsers(ids);
  }
  
  @DeleteMapping("/groups/users/batch")
  public void delUsers(@NotNull @RequestBody Set<GroupUserPK> ids) {
    groupService.delUsers(ids);
  }
  
  @GetMapping("/groups/getTreeByParentId")
  public MyTree<Group> tree(@Nullable @RequestParam("parentId") String parentId){
    return groupService.getTree(parentId);
  }
  
  
}

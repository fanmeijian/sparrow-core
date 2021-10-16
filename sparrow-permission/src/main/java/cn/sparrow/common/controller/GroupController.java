package cn.sparrow.common.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

import cn.sparrow.common.service.GroupService;
import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.group.Group;
import cn.sparrow.model.group.GroupLevelPK;
import cn.sparrow.model.group.GroupOrganizationPK;
import cn.sparrow.model.group.GroupRelationPK;
import cn.sparrow.model.group.GroupRolePK;
import cn.sparrow.model.group.GroupSysrolePK;
import cn.sparrow.model.group.GroupUserPK;

@RestController
public class GroupController {
  @Autowired GroupService groupService;
  
  
  @PostMapping("/groups/relations/batch")
  public void addRelations(@NotNull @RequestBody Set<GroupRelationPK> ids) {
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
  public void addLevels(@NotNull @RequestBody Set<GroupLevelPK> ids) {
    groupService.addLevels(ids);
  }
  
  @DeleteMapping("/groups/levels/batch")
  public void delLevels(@NotNull @RequestBody Set<GroupLevelPK> ids) {
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

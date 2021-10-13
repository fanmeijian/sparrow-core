package cn.sparrow.common.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.sparrow.model.organization.GroupLevelPK;
import cn.sparrow.model.organization.GroupOrganizationPK;
import cn.sparrow.model.organization.GroupRolePK;
import cn.sparrow.model.organization.GroupSysrolePK;
import cn.sparrow.model.organization.GroupUserPK;
import cn.sparrow.organization.service.GroupService;

@RestController
public class GroupController {
  @Autowired GroupService groupService;
  
  @PostMapping("/groups/organizations")
  public void addOrganizations(Set<GroupOrganizationPK> ids) {
    groupService.addOrganizations(ids);
  }
  
  @DeleteMapping("/groups/organizations")
  public void delOrganizations(Set<GroupOrganizationPK> ids) {
    groupService.delOrganizations(ids);
  }
  
  @PostMapping("/groups/roles")
  public void addRoles(Set<GroupRolePK> ids) {
    groupService.addRoles(ids);
  }
  
  @DeleteMapping("/groups/roles")
  public void delRoles(Set<GroupRolePK> ids) {
    groupService.delRoles(ids);
  }
  
  @PostMapping("/groups/levels")
  public void addLevels(Set<GroupLevelPK> ids) {
    groupService.addLevels(ids);
  }
  
  @DeleteMapping("/groups/levels")
  public void delLevels(Set<GroupLevelPK> ids) {
    groupService.delLevels(ids);
  }
  
  @PostMapping("/groups/sysroles")
  public void addSysroles(Set<GroupSysrolePK> ids) {
    groupService.addSysroles(ids);
  }
  
  @DeleteMapping("/groups/sysroles")
  public void delSysroles(Set<GroupSysrolePK> ids) {
    groupService.delSysroles(ids);
  }
  
  @PostMapping("/groups/users")
  public void addUsers(Set<GroupUserPK> ids) {
    groupService.addUsers(ids);
  }
  
  @DeleteMapping("/groups/users")
  public void delUsers(Set<GroupUserPK> ids) {
    groupService.delUsers(ids);
  }
  
}

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
import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.Organization;
import cn.sparrow.model.organization.OrganizationGroupPK;
import cn.sparrow.model.organization.OrganizationLevelPK;
import cn.sparrow.model.organization.OrganizationRelationPK;
import cn.sparrow.model.organization.OrganizationRolePK;
import cn.sparrow.organization.service.OrganizationService;

@RestController
public class OrganizationController {

  @Autowired OrganizationService organizationService;
  
  
  @PostMapping("/organizations/relations")
  public void addRelations(@NotNull @RequestBody Set<OrganizationRelationPK> ids) {
    organizationService.addRelations(ids);
  }
  
  @DeleteMapping("/organizations/relations")
  public void delRelations(@NotNull @RequestBody Set<OrganizationRelationPK> ids) {
    organizationService.delRelations(ids);
  }
  
  @PostMapping("/organizations/roles")
  public void addRoles(@NotNull @RequestBody Set<OrganizationRolePK> ids) {
    organizationService.addRoles(ids);
  }
  
  @DeleteMapping("/organizations/roles")
  public void delRoles(@NotNull @RequestBody Set<OrganizationRolePK> ids) {
    organizationService.delRoles(ids);
  }
  
  @PostMapping("/organizations/levels")
  public void addLevels(@NotNull @RequestBody Set<OrganizationLevelPK> ids) {
    organizationService.addLevels(ids);
  }
  
  @DeleteMapping("/organizations/levels")
  public void delLevels(@NotNull @RequestBody Set<OrganizationLevelPK> ids) {
    organizationService.delLevels(ids);
  }
  
  @PostMapping("/organizations/groups")
  public void addGroups(@NotNull @RequestBody Set<OrganizationGroupPK> ids) {
    organizationService.addGroups(ids);
  }
  
  @DeleteMapping("/organizations/groups")
  public void delGroups(@NotNull @RequestBody Set<OrganizationGroupPK> ids) {
    organizationService.delGroups(ids);
  }
  
  @GetMapping("/orgranizations/getTreeByParentId")
  public MyTree<Organization> tree(@Nullable @RequestParam("parentId") String parentId){
    return organizationService.getTree(parentId);
  }
  
}

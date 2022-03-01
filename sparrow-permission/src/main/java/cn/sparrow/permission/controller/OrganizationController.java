package cn.sparrow.permission.controller;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.sparrow.permission.model.SparrowTree;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationGroup;
import cn.sparrow.permission.model.organization.OrganizationGroupPK;
import cn.sparrow.permission.model.organization.OrganizationPositionLevel;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationRelation;
import cn.sparrow.permission.model.organization.OrganizationRelationPK;
import cn.sparrow.permission.model.organization.OrganizationRole;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import cn.sparrow.permission.repository.organization.OrganizationRepository;
import cn.sparrow.permission.service.organization.OrganizationService;

@RestController
public class OrganizationController {

  @Autowired OrganizationService organizationService;
  @Autowired OrganizationRepository organizationRepository;
  
//  @PostMapping("/organizations")
//  public Organization add(@NotNull @RequestBody Organization organization) {
//    return organizationService.add(organization);
//    
//  }
  
  @GetMapping("/organizations/getChildren")
  public List<Organization> getChildren(@NotNull @RequestParam("parentId") final String organizationId){
    return organizationService.getChildren(organizationId);
  }
  
  @GetMapping("/organizations/getParents")
  public List<Organization> getParents(@NotNull @RequestParam("parentId") final String organizationId){
    return organizationService.getParents(organizationId);
  }
  
  @GetMapping("/organizations/getRoles")
  public List<OrganizationRole> getRoles(@NotBlank @RequestParam("organizationId") final String organizationId){
    return organizationService.getOrganizationRoles(organizationId);
  }
  
  @GetMapping("/organizations/getLevels")
  public List<OrganizationPositionLevel> getLevels(@NotBlank @RequestParam("organizationId") final String organizationId){
    return organizationService.getOrganizationLevels(organizationId);
  }
  
  @GetMapping("/organizations/getGroups")
  public List<OrganizationGroup> getGroups(@NotBlank @RequestParam("organizationId") final String organizationId){
    return organizationService.getOrganizationGroups(organizationId);
  }
  
  @GetMapping("/organizations/getEmployees")
  public List<Employee> getEmployees(@NotBlank @RequestParam("organizationId") final String organizationId){
    return organizationService.getEmployees(organizationId, Pageable.unpaged());
  }
  
  @GetMapping("/organizations/getModelName")
	public String getModelName() {
		return "{\"modelName\":\"" +Organization.class.getName() + "\"}";
	}
  
  @PostMapping("/organizations/batch")
  public void add(@NotNull @RequestBody List<Organization> organizations) {
    organizationRepository.saveAll(organizations);
  }
  
  @PatchMapping("/organizations/batch")
  public void update(@NotNull @RequestBody List<Organization> organizations) {
    organizationRepository.saveAll(organizations);
  }
  
  @DeleteMapping("/organizations/batch")
  public void del(@NotNull @RequestBody String[] ids) {
	  organizationService.delBatch(ids);
  }
  
  
  @PostMapping("/organizations/addRelation")
  public void addRelations(@NotNull @RequestBody Set<OrganizationRelation> organizationRelation) {
    organizationService.addRelations(organizationRelation);
  }
  
  @DeleteMapping("/organizations/removeRelation")
  public void removeRelations(@NotNull @RequestBody Set<OrganizationRelationPK> ids) {
    organizationService.removeRelations(ids);
  }
  
  @DeleteMapping("/organizations/updateParent")
  public void updateParent(@NotNull @RequestBody Set<OrganizationRelation> organizationRelation) {
    organizationService.updateParent(organizationRelation);
  }
  
  @PostMapping("/organizations/roles/batch")
  public void addRoles(@NotNull @RequestBody Set<OrganizationRolePK> ids) {
    organizationService.addRoles(ids);
  }
  
  @DeleteMapping("/organizations/roles/batch")
  public void delRoles(@NotNull @RequestBody Set<OrganizationRolePK> ids) {
    organizationService.delRoles(ids);
  }
  
  @PostMapping("/organizations/levels/batch")
  public void addLevels(@NotNull @RequestBody Set<OrganizationPositionLevelPK> ids) {
    organizationService.addLevels(ids);
  }
  
  @DeleteMapping("/organizations/levels/batch")
  public void delLevels(@NotNull @RequestBody Set<OrganizationPositionLevelPK> ids) {
    organizationService.delLevels(ids);
  }
  
  @PostMapping("/organizations/groups/batch")
  public void addGroups(@NotNull @RequestBody Set<OrganizationGroupPK> ids) {
    organizationService.addGroups(ids);
  }
  
  @DeleteMapping("/organizations/groups/batch")
  public void delGroups(@NotNull @RequestBody Set<OrganizationGroupPK> ids) {
    organizationService.delGroups(ids);
  }
  
  @GetMapping("/orgranizations/getTreeByParentId")
  public SparrowTree<Organization, String> tree(@Nullable @RequestParam("parentId") String parentId){
    return organizationService.getTree(parentId==null||parentId.isBlank()?null:parentId);
  }
  
}

package cn.sparrow.common.controller;

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

import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.Employee;
import cn.sparrow.model.organization.Organization;
import cn.sparrow.model.organization.OrganizationGroup;
import cn.sparrow.model.organization.OrganizationGroupPK;
import cn.sparrow.model.organization.OrganizationPositionLevel;
import cn.sparrow.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.model.organization.OrganizationRelation;
import cn.sparrow.model.organization.OrganizationRelationPK;
import cn.sparrow.model.organization.OrganizationRole;
import cn.sparrow.model.organization.OrganizationRolePK;
import cn.sparrow.organization.repository.OrganizationRepository;
import cn.sparrow.organization.service.OrganizationService;

@RestController
public class OrganizationController {

  @Autowired OrganizationService organizationService;
  @Autowired OrganizationRepository organizationRepository;
  
  @PostMapping("/organizations")
  public Organization add(@NotNull @RequestBody Organization organization) {
    return organizationService.add(organization);
    
  }
  
  @GetMapping("/organizations/getChildren")
  public List<OrganizationRelation> getChildren(@NotNull @RequestParam("parentId") final String organizationId){
    return organizationService.getChildren(organizationId);
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
  
  
  @PostMapping("/organizations/relations/batch")
  public void addRelations(@NotNull @RequestBody Set<OrganizationRelationPK> ids) {
    organizationService.addRelations(ids);
  }
  
  @DeleteMapping("/organizations/relations/batch")
  public void delRelations(@NotNull @RequestBody Set<OrganizationRelationPK> ids) {
    organizationService.delRelations(ids);
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
  public MyTree<Organization> tree(@Nullable @RequestParam("parentId") String parentId){
    return organizationService.getTree(parentId==null||parentId.isBlank()?null:parentId);
  }
  
}

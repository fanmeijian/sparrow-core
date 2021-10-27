package cn.sparrow.organization.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.Employee;
import cn.sparrow.model.organization.Organization;
import cn.sparrow.model.organization.OrganizationGroup;
import cn.sparrow.model.organization.OrganizationGroupPK;
import cn.sparrow.model.organization.OrganizationLevel;
import cn.sparrow.model.organization.OrganizationLevelPK;
import cn.sparrow.model.organization.OrganizationRelation;
import cn.sparrow.model.organization.OrganizationRelationPK;
import cn.sparrow.model.organization.OrganizationRole;
import cn.sparrow.model.organization.OrganizationRolePK;
import cn.sparrow.organization.repository.EmployeeRepository;
import cn.sparrow.organization.repository.OrganizationGroupRepository;
import cn.sparrow.organization.repository.OrganizationLevelRepository;
import cn.sparrow.organization.repository.OrganizationRelationRepository;
import cn.sparrow.organization.repository.OrganizationRepository;
import cn.sparrow.organization.repository.OrganizationRoleRepository;

@Service
public class OrganizationService {

  @Autowired
  OrganizationRelationRepository organizationRelationRepository;
  @Autowired
  OrganizationRoleRepository organizationRoleRepository;
  @Autowired
  OrganizationLevelRepository organizationLevelRepository;
  @Autowired
  OrganizationGroupRepository organizationGroupRepository;
  @Autowired
  OrganizationRepository organizationRepository;
  @Autowired RoleService roleService;
  @Autowired LevelService levelService;
  @Autowired EmployeeRepository employeeRepository;

  public Organization add(Organization organization) {
    Organization org = organizationRepository.save(organization);
    if (organization.getParentIds() != null) {
      organization.getParentIds().forEach(f -> {
        organizationRelationRepository
            .save(new OrganizationRelation(new OrganizationRelationPK(org.getId(), f)));
      });
    }
    return org;
  }

  public List<OrganizationRole> getOrganizationRoles(@NotBlank String organizationId) {
    List<OrganizationRole> roles = organizationRoleRepository.findByIdOrganizationId(organizationId);
    roles.forEach(f->{
      f.setHasChildren(roleService.getChildrent(f.getId()).size()>0?true:false);
    });
    return roles;
  }
  
  public List<OrganizationLevel> getOrganizationLevels(@NotBlank String organizationId) {
	    List<OrganizationLevel> roles = organizationLevelRepository.findByIdOrganizationId(organizationId);
	    roles.forEach(f->{
	      f.setHasChildren(levelService.getChildrent(f.getId()).size()>0?true:false);
	    });
	    return roles;
	  }
  
  public List<Employee> getEmployees(@NotBlank String organizationId, Pageable pageable) {
	  return employeeRepository.findByOrganizationId(organizationId, pageable);
	}

  public Set<OrganizationRelation> getChildren(String parentId) {
    Set<OrganizationRelation> organizationRelations = new HashSet<OrganizationRelation>();
    if (parentId == null || parentId.isBlank()) {

      organizationRepository.findByRoot(true).forEach(f -> {
        organizationRelations.add(new OrganizationRelation(f));
      });
    } else
      organizationRelations.addAll(organizationRelationRepository.findByIdParentId(parentId));
    organizationRelations.forEach(f -> {
      f.getOrganization().setHasChildren(
          organizationRelationRepository.countByIdParentId(f.getOrganization().getId()) > 0 ? true
              : false);
    });
    return organizationRelations;
  }

  @Transactional
  public void delBatch(String[] ids) {
    organizationRelationRepository.deleteByIdOrganizationIdInOrIdParentIdIn(ids, ids);
    organizationRepository.deleteByIdIn(ids);
  }

  public void addRelations(Set<OrganizationRelationPK> ids) {
    ids.forEach(f -> {
      organizationRelationRepository.saveAndFlush(new OrganizationRelation(f));
    });
  }

  public void delRelations(Set<OrganizationRelationPK> ids) {
    ids.forEach(f -> {
      organizationRelationRepository.delete(new OrganizationRelation(f));
    });
  }

  public void addRoles(Set<OrganizationRolePK> ids) {
    ids.forEach(f -> {
      organizationRoleRepository.saveAndFlush(new OrganizationRole(f));
    });
  }

  public void delRoles(Set<OrganizationRolePK> ids) {
    ids.forEach(f -> {
      organizationRoleRepository.delete(new OrganizationRole(f));
    });
  }

  public void addLevels(Set<OrganizationLevelPK> ids) {
    ids.forEach(f -> {
      organizationLevelRepository.saveAndFlush(new OrganizationLevel(f));
    });
  }

  public void delLevels(Set<OrganizationLevelPK> ids) {
    ids.forEach(f -> {
      organizationLevelRepository.delete(new OrganizationLevel(f));
    });
  }

  public void addGroups(Set<OrganizationGroupPK> ids) {
    ids.forEach(f -> {
      organizationGroupRepository.saveAndFlush(new OrganizationGroup(f));
    });
  }

  public void delGroups(Set<OrganizationGroupPK> ids) {
    ids.forEach(f -> {
      organizationGroupRepository.delete(new OrganizationGroup(f));
    });
  }

  public MyTree<Organization> getTree(String parentId) {

    if (parentId == null) {
      MyTree<Organization> rootTree = new MyTree<Organization>(null);
      organizationRepository.findByRoot(true).forEach(f -> {
        MyTree<Organization> myTree = new MyTree<Organization>(f);
        buildTree(myTree);
        rootTree.getChildren().add(myTree);
      });

      return rootTree;
    } else {
      MyTree<Organization> myTree =
          new MyTree<Organization>(organizationRepository.findById(parentId).orElse(null));
      buildTree(myTree);
      return myTree;
    }


  }

  public void buildTree(MyTree<Organization> myTree) {
    organizationRelationRepository
        .findByIdParentId(myTree.getMe() == null ? null : myTree.getMe().getId()).forEach(f -> {
          MyTree<Organization> leaf = new MyTree<Organization>(f.getOrganization());
          // 防止死循环
          if (organizationRelationRepository.findById(
              new OrganizationRelationPK(f.getId().getParentId(), f.getId().getOrganizationId()))
              .orElse(null) == null)
            buildTree(leaf);
          myTree.getChildren().add(leaf);
        });
  }



}

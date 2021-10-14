package cn.sparrow.organization.service;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.Organization;
import cn.sparrow.model.organization.OrganizationGroup;
import cn.sparrow.model.organization.OrganizationGroupPK;
import cn.sparrow.model.organization.OrganizationLevel;
import cn.sparrow.model.organization.OrganizationLevelPK;
import cn.sparrow.model.organization.OrganizationRelation;
import cn.sparrow.model.organization.OrganizationRelationPK;
import cn.sparrow.model.organization.OrganizationRole;
import cn.sparrow.model.organization.OrganizationRolePK;
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
  @Autowired OrganizationRepository organizationRepository;


  public void addRelations(Set<OrganizationRelationPK> ids) {
    ids.forEach(f -> {
      organizationRelationRepository.save(new OrganizationRelation(f));
    });
  }

  public void delRelations(Set<OrganizationRelationPK> ids) {
    ids.forEach(f -> {
      organizationRelationRepository.delete(new OrganizationRelation(f));
    });
  }

  public void addRoles(Set<OrganizationRolePK> ids) {
    ids.forEach(f -> {
      organizationRoleRepository.save(new OrganizationRole(f));
    });
  }

  public void delRoles(Set<OrganizationRolePK> ids) {
    ids.forEach(f -> {
      organizationRoleRepository.delete(new OrganizationRole(f));
    });
  }

  public void addLevels(Set<OrganizationLevelPK> ids) {
    ids.forEach(f -> {
      organizationLevelRepository.save(new OrganizationLevel(f));
    });
  }

  public void delLevels(Set<OrganizationLevelPK> ids) {
    ids.forEach(f -> {
      organizationLevelRepository.delete(new OrganizationLevel(f));
    });
  }

  public void addGroups(Set<OrganizationGroupPK> ids) {
    ids.forEach(f -> {
      organizationGroupRepository.save(new OrganizationGroup(f));
    });
  }

  public void delGroups(Set<OrganizationGroupPK> ids) {
    ids.forEach(f -> {
      organizationGroupRepository.delete(new OrganizationGroup(f));
    });
  }

  public MyTree<Organization> getTree(String parentId) {
    MyTree<Organization> myTree = new MyTree<Organization>(parentId==null?null:organizationRepository.findById(parentId).orElse(null));
    buildTree(myTree);
    return myTree;
  }

  public void buildTree(MyTree<Organization> myTree) {
    organizationRelationRepository.findByParentId(myTree.getMe()==null?null:myTree.getMe().getId()).forEach(f -> {
      MyTree<Organization> leaf = new MyTree<Organization>(f.getOrganization());
      // 防止死循环
      if(organizationRelationRepository.findById(new OrganizationRelationPK(f.getId().getParentId(), f.getId().getOrganizationId())).orElse(null)==null)
        buildTree(leaf);
      myTree.getChildren().add(leaf);
    });
  }

}

package cn.sparrow.organization.service;

import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.OrganizationRoleRelationPK;
import cn.sparrow.model.organization.OrganizationRole;
import cn.sparrow.model.organization.OrganizationRolePK;
import cn.sparrow.model.organization.OrganizationRoleRelation;
import cn.sparrow.model.organization.Role;
import cn.sparrow.model.organization.RoleRelation;
import cn.sparrow.model.organization.RoleRelationPK;
import cn.sparrow.organization.repository.OrganizationRoleRelationRepository;
import cn.sparrow.organization.repository.OrganizationRoleRepository;
import cn.sparrow.organization.repository.RoleRelationRepository;
import cn.sparrow.organization.repository.RoleRepository;

@Service
public class RoleService {

  @Autowired
  RoleRelationRepository roleRelationRepository;
  @Autowired
  RoleRepository roleRepository;
  @Autowired
  OrganizationRoleRepository organizationRoleRepository;
  @Autowired
  OrganizationRoleRelationRepository organizationRoleRelationRepository;

  public List<OrganizationRoleRelation> getChildrent(@NotNull OrganizationRolePK parentId) {
    return organizationRoleRelationRepository.findByIdParentId(parentId);
  }

  public void addRelations(Set<RoleRelationPK> ids) {
    ids.forEach(f -> {
      roleRelationRepository.save(new RoleRelation(f));
    });
  }

  public void delRelations(Set<RoleRelationPK> ids) {
    ids.forEach(f -> {
      roleRelationRepository.deleteById(f);
    });
  }

  public void addRelations(List<OrganizationRoleRelationPK> ids) {
    ids.forEach(f -> {
      organizationRoleRelationRepository.save(new OrganizationRoleRelation(f));
    });
  }

  public void delRelations(List<OrganizationRoleRelationPK> ids) {
    ids.forEach(f -> {
      organizationRoleRelationRepository.deleteById(f);
    });
  }

  public MyTree<Role> getTree(String parentId) {
    if (parentId == null) {
      MyTree<Role> rootTree = new MyTree<Role>(null);
      roleRepository.findByRoot(true).forEach(f -> {
        MyTree<Role> myTree = new MyTree<Role>(f);
        buildTree(myTree);
        rootTree.getChildren().add(myTree);
      });

      return rootTree;
    } else {
      MyTree<Role> myTree = new MyTree<Role>(roleRepository.findById(parentId).orElse(null));
      buildTree(myTree);
      return myTree;
    }
  }

  public void buildTree(MyTree<Role> myTree) {
    roleRelationRepository.findByIdParentId(myTree.getMe() == null ? null : myTree.getMe().getId())
        .forEach(f -> {
          MyTree<Role> leaf = new MyTree<Role>(f.getRole());
          // 防止死循环
          if (roleRelationRepository
              .findById(new RoleRelationPK(f.getId().getParentId(), f.getId().getRoleId()))
              .orElse(null) == null)
            buildTree(leaf);
          myTree.getChildren().add(leaf);
        });
  }

  @Transactional
  public Role save(Role role) {
    Role savedRole = roleRepository.save(role);

    // 保存岗位所在的组织
    if (role.getOrganizationIds() != null) {

      role.getOrganizationIds().forEach(f -> {
        OrganizationRole organizationRole = organizationRoleRepository
            .save(new OrganizationRole(new OrganizationRolePK(f, savedRole.getId())));

        // 保存岗位之间的关系,注意这里的岗位关系指的是组织岗位之间的关系，因为岗位独立于组织没有意义
        if (role.getParentIds() != null) {
          role.getParentIds().forEach(parentId -> {
            organizationRoleRelationRepository.save(new OrganizationRoleRelation(
                new OrganizationRoleRelationPK(organizationRole.getId(), parentId)));
          });
        }
      });
    }

    return savedRole;
  }

  @Transactional
  public void delBatch(String[] ids) {
    roleRelationRepository.deleteByIdRoleIdInOrIdParentIdIn(ids, ids);
    roleRepository.deleteByIdIn(ids);
  }
}

package cn.sparrow.organization.service;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.group.GroupRelation;
import cn.sparrow.model.group.GroupRelationPK;
import cn.sparrow.model.organization.Organization;
import cn.sparrow.model.organization.OrganizationRelation;
import cn.sparrow.model.organization.OrganizationRelationPK;
import cn.sparrow.model.organization.Role;
import cn.sparrow.model.organization.RoleRelation;
import cn.sparrow.model.organization.RoleRelationPK;
import cn.sparrow.organization.repository.RoleRelationRepository;
import cn.sparrow.organization.repository.RoleRepository;

@Service
public class RoleService {

  @Autowired RoleRelationRepository roleRelationRepository;
  @Autowired RoleRepository roleRepository;
  
  
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
  
  
  public MyTree<Role> getTree(String parentId) {
    MyTree<Role> myTree = new MyTree<Role>(parentId==null?null:roleRepository.findById(parentId).orElse(null));
    buildTree(myTree);
    return myTree;
  }

  public void buildTree(MyTree<Role> myTree) {
    roleRelationRepository.findByIdParentId(myTree.getMe()==null?null:myTree.getMe().getId()).forEach(f -> {
      MyTree<Role> leaf = new MyTree<Role>(f.getRole());
      // 防止死循环
      if(roleRelationRepository.findById(new RoleRelationPK(f.getId().getParentId(), f.getId().getRoleId())).orElse(null)==null)
        buildTree(leaf);
      myTree.getChildren().add(leaf);
    });
  }
}

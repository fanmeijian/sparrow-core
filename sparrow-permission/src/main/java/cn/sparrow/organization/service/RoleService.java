package cn.sparrow.organization.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.Role;
import cn.sparrow.model.organization.RoleRelation;
import cn.sparrow.model.organization.RoleRelationPK;
import cn.sparrow.organization.repository.RoleRelationRepository;
import cn.sparrow.organization.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRelationRepository roleRelationRepository;
	@Autowired
	RoleRepository roleRepository;

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
		roleRelationRepository.findByIdParentId(myTree.getMe() == null ? null : myTree.getMe().getId()).forEach(f -> {
			MyTree<Role> leaf = new MyTree<Role>(f.getRole());
			// 防止死循环
			if (roleRelationRepository.findById(new RoleRelationPK(f.getId().getParentId(), f.getId().getRoleId()))
					.orElse(null) == null)
				buildTree(leaf);
			myTree.getChildren().add(leaf);
		});
	}

	public Role save(Role role) {
		Role r = roleRepository.save(role);
		if (role.getParentId() != null)
			roleRelationRepository.save(
					new RoleRelation(new RoleRelationPK(r.getId(), role.getParentId())));
		return r;
	}
}

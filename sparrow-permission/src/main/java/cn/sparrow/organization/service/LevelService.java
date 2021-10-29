package cn.sparrow.organization.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.EmployeeOrganizationLevel;
import cn.sparrow.model.organization.Level;
import cn.sparrow.model.organization.LevelRelationPK;
import cn.sparrow.model.organization.OrganizationLevel;
import cn.sparrow.model.organization.OrganizationLevelPK;
import cn.sparrow.model.organization.OrganizationLevelRelation;
import cn.sparrow.organization.repository.EmployeeOrganizationLevelRepository;
import cn.sparrow.organization.repository.LevelRelationRepository;
import cn.sparrow.organization.repository.LevelRepository;
import cn.sparrow.organization.repository.OrganizationLevelRelationRepository;
import cn.sparrow.organization.repository.OrganizationLevelRepository;

@Service
public class LevelService {

	@Autowired
	LevelRelationRepository levelRelationRepository;
	@Autowired
	LevelRepository levelRepository;
	@Autowired
	OrganizationLevelRelationRepository organizationLevelRelationRepository;
	@Autowired
	OrganizationLevelRepository organizationLevelRepository;
	@Autowired EmployeeOrganizationLevelRepository employeeOrganizationLevelRepository;

	public List<EmployeeOrganizationLevel> getEmployees(OrganizationLevelPK organizationLevelId) {
		return employeeOrganizationLevelRepository.findByIdOrganizationLevelId(organizationLevelId);
	}
	
	@Transactional
	public Level save(Level lvel) {
		Level savedLevel = levelRepository.save(lvel);
		// 保存岗位所在的组织
		if (lvel.getOrganizationIds() != null) {
			lvel.getOrganizationIds().forEach(f -> {
				organizationLevelRepository.save(new OrganizationLevel(new OrganizationLevelPK(f, savedLevel.getId())));
			});
		}
		return savedLevel;
	}

	public void addRelations(List<OrganizationLevelRelation> organizationLevelRelations) {
		organizationLevelRelationRepository.saveAll(organizationLevelRelations);

	}

	public void delRelations(Set<LevelRelationPK> ids) {
		ids.forEach(f -> {
			levelRelationRepository.deleteById(f);
		});
	}

	public MyTree<Level> getTree(String parentId) {
		if (parentId == null) {
			MyTree<Level> rootTree = new MyTree<Level>(null);
			levelRepository.findByRoot(true).forEach(f -> {
				MyTree<Level> myTree = new MyTree<Level>(f);
				buildTree(myTree);
				rootTree.getChildren().add(myTree);
			});

			return rootTree;
		} else {
			MyTree<Level> myTree = new MyTree<Level>(levelRepository.findById(parentId).orElse(null));
			buildTree(myTree);
			return myTree;
		}
	}

	public void buildTree(MyTree<Level> myTree) {
		levelRelationRepository.findByIdParentId(myTree.getMe() == null ? null : myTree.getMe().getId()).forEach(f -> {
			MyTree<Level> leaf = new MyTree<Level>(f.getLevel());
			// 防止死循环
			if (levelRelationRepository.findById(new LevelRelationPK(f.getId().getParentId(), f.getId().getLevelId()))
					.orElse(null) == null)
				buildTree(leaf);
			myTree.getChildren().add(leaf);
		});
	}

	@Transactional
	public void delBatch(String[] ids) {
		levelRelationRepository.deleteByIdLevelIdInOrIdParentIdIn(ids, ids);
		levelRepository.deleteByIdIn(ids);
	}

	public List<OrganizationLevelRelation> getChildren(OrganizationLevelPK parentId) {
		return organizationLevelRelationRepository.findByIdParentId(parentId);
	}
	
	public List<OrganizationLevelRelation> getParents(OrganizationLevelPK id) {
		return organizationLevelRelationRepository.findByIdId(id);
	}
}

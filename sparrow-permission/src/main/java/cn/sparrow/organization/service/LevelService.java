package cn.sparrow.organization.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.Level;
import cn.sparrow.model.organization.LevelRelation;
import cn.sparrow.model.organization.LevelRelationPK;
import cn.sparrow.organization.repository.LevelRelationRepository;
import cn.sparrow.organization.repository.LevelRepository;

@Service
public class LevelService {

	@Autowired
	LevelRelationRepository levelRelationRepository;
	@Autowired
	LevelRepository levelRepository;

	public Level save(Level role) {
		Level level = levelRepository.save(role);
		if (level.getParentIds() != null) {
			level.getParentIds().forEach(f -> {
				levelRelationRepository.save(new LevelRelation(new LevelRelationPK(level.getId(), f)));
			});
		}
		return level;
	}

	public void addRelations(Set<LevelRelationPK> ids) {
		ids.forEach(f -> {
			levelRelationRepository.save(new LevelRelation(f));
		});
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
}

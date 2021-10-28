package cn.sparrow.common.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.Level;
import cn.sparrow.model.organization.LevelRelationPK;
import cn.sparrow.model.organization.OrganizationLevelPK;
import cn.sparrow.model.organization.OrganizationLevelRelation;
import cn.sparrow.model.organization.Role;
import cn.sparrow.organization.repository.LevelRepository;
import cn.sparrow.organization.service.LevelService;

@RestController
public class LevelController {

	@Autowired
	LevelService levelService;
	@Autowired
	LevelRepository levelRepository;

	@PostMapping("/levels")
	public Level save(@NotNull @RequestBody Level level) {
		return levelService.save(level);
	}
	
	@GetMapping("/levels/getChildren")
	public List<OrganizationLevelRelation> getChildren(@NotNull @RequestParam("organizationId") final String organizationId,@NotNull @RequestParam("positionLevelId") final String positionLevelId){
	  return levelService.getChildren(new OrganizationLevelPK(organizationId, positionLevelId));
	}
	
	@GetMapping("/levels/getParents")
	public List<OrganizationLevelRelation> getParents(@NotNull @RequestParam("organizationId") final String organizationId,@NotNull @RequestParam("positionLevelId") final String positionLevelId){
	  return levelService.getParents(new OrganizationLevelPK(organizationId, positionLevelId));
	}

	@GetMapping("/levels/getModelName")
	public String getModelName() {
		return "{\"modelName\":\"" + Role.class.getName() + "\"}";
	}

	@PostMapping("/levels/batch")
	public void add(@NotNull @RequestBody final List<Level> levels) {
		levelRepository.saveAll(levels);
	}

	@PatchMapping("/levels/batch")
	public void update(@NotNull @RequestBody final List<Level> levels) {
		levelRepository.saveAll(levels);
	}

	@DeleteMapping("/levels/batch")
	public void delete(@NotNull @RequestBody final String[] ids) {
		levelService.delBatch(ids);
	}

	@PostMapping("/levels/relations/batch")
	public void addRelations(@NotNull @RequestBody List<OrganizationLevelRelation> organizationLevelRelations) {
		levelService.addRelations(organizationLevelRelations);
	}

	@DeleteMapping("/levels/relations/batch")
	public void delRelations(@NotNull @RequestBody Set<LevelRelationPK> ids) {
		levelService.delRelations(ids);
	}

	@GetMapping("/levels/getTreeByParentId")
	public MyTree<Level> tree(@Nullable @RequestParam("parentId") String parentId) {
		return levelService.getTree(parentId == null || parentId.isBlank() ? null : parentId);
	}
}

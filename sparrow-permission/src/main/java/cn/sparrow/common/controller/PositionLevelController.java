package cn.sparrow.common.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

import cn.sparrow.model.organization.PositionLevel;
import cn.sparrow.model.organization.Organization;
import cn.sparrow.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.model.organization.OrganizationPositionLevelRelation;
import cn.sparrow.model.organization.Role;
import cn.sparrow.organization.repository.PositionLevelRepository;
import cn.sparrow.organization.service.PositionLevelService;

@RestController
public class PositionLevelController {

	@Autowired
	PositionLevelService positionLevelService;
	@Autowired
	PositionLevelRepository positionLevelRepository;

	@PostMapping("/levels")
	public PositionLevel save(@NotNull @RequestBody PositionLevel level) {
		return positionLevelService.save(level);
	}
	
	@GetMapping("/levels/getChildren")
	public List<OrganizationPositionLevelRelation> getChildren(@NotNull @RequestParam("organizationId") final String organizationId,@NotNull @RequestParam("positionLevelId") final String positionLevelId){
	  return positionLevelService.getChildren(new OrganizationPositionLevelPK(organizationId, positionLevelId));
	}
	
	@GetMapping("/levels/getParents")
	public List<OrganizationPositionLevelRelation> getParents(@NotNull @RequestParam("organizationId") final String organizationId,@NotNull @RequestParam("positionLevelId") final String positionLevelId){
	  return positionLevelService.getParents(new OrganizationPositionLevelPK(organizationId, positionLevelId));
	}
	
	@GetMapping("/levels/getParentOrganizations")
	public List<Organization> getParentOrganizations(@NotBlank @RequestParam("positionLevelId") final String positionLevelId){
		return positionLevelService.getParentOrganizations(positionLevelId);
	}

	@GetMapping("/levels/getModelName")
	public String getModelName() {
		return "{\"modelName\":\"" + Role.class.getName() + "\"}";
	}

	@PostMapping("/levels/batch")
	public void add(@NotNull @RequestBody final List<PositionLevel> levels) {
		positionLevelRepository.saveAll(levels);
	}

	@PatchMapping("/levels/batch")
	public void update(@NotNull @RequestBody final List<PositionLevel> levels) {
		positionLevelRepository.saveAll(levels);
	}


	@PostMapping("/levels/relations/batch")
	public void addRelations(@NotNull @RequestBody List<OrganizationPositionLevelRelation> organizationLevelRelations) {
		positionLevelService.addRelations(organizationLevelRelations);
	}
}

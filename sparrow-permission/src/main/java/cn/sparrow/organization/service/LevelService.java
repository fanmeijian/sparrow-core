package cn.sparrow.organization.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.organization.EmployeeOrganizationLevel;
import cn.sparrow.model.organization.PositionLevel;
import cn.sparrow.model.organization.OrganizationLevel;
import cn.sparrow.model.organization.OrganizationLevelPK;
import cn.sparrow.model.organization.OrganizationLevelRelation;
import cn.sparrow.organization.repository.EmployeeOrganizationLevelRepository;
import cn.sparrow.organization.repository.LevelRepository;
import cn.sparrow.organization.repository.OrganizationLevelRelationRepository;
import cn.sparrow.organization.repository.OrganizationLevelRepository;

@Service
public class LevelService {

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
	public PositionLevel save(PositionLevel lvel) {
		PositionLevel savedLevel = levelRepository.save(lvel);
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

	@Transactional
	public void delBatch(String[] ids) {
		levelRepository.deleteByIdIn(ids);
	}

	public List<OrganizationLevelRelation> getChildren(OrganizationLevelPK parentId) {
		return organizationLevelRelationRepository.findByIdParentId(parentId);
	}
	
	public List<OrganizationLevelRelation> getParents(OrganizationLevelPK id) {
		return organizationLevelRelationRepository.findByIdId(id);
	}
}

package cn.sparrow.permission.service.organization;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.organization.EmployeeOrganizationLevel;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationPositionLevel;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelation;
import cn.sparrow.permission.model.organization.PositionLevel;
import cn.sparrow.permission.repository.organization.EmployeeOrganizationLevelRepository;
import cn.sparrow.permission.repository.organization.OrganizationLevelRelationRepository;
import cn.sparrow.permission.repository.organization.OrganizationLevelRepository;
import cn.sparrow.permission.repository.organization.PositionLevelRepository;

@Service
public class PositionLevelService {

	@Autowired
	PositionLevelRepository levelRepository;
	@Autowired
	OrganizationLevelRelationRepository organizationLevelRelationRepository;
	@Autowired
	OrganizationLevelRepository organizationLevelRepository;
	@Autowired EmployeeOrganizationLevelRepository employeeOrganizationLevelRepository;

	public List<EmployeeOrganizationLevel> getEmployees(OrganizationPositionLevelPK organizationLevelId) {
		return employeeOrganizationLevelRepository.findByIdOrganizationLevelId(organizationLevelId);
	}
	
	@Transactional
	public PositionLevel save(PositionLevel lvel) {
		PositionLevel savedLevel = levelRepository.save(lvel);
		// 保存岗位所在的组织
		if (lvel.getOrganizationIds() != null) {
			lvel.getOrganizationIds().forEach(f -> {
				organizationLevelRepository.save(new OrganizationPositionLevel(new OrganizationPositionLevelPK(f, savedLevel.getId())));
			});
		}
		return savedLevel;
	}

	public void addRelations(List<OrganizationPositionLevelRelation> organizationLevelRelations) {
		organizationLevelRelationRepository.saveAll(organizationLevelRelations);

	}

	@Transactional
	public void delBatch(String[] ids) {
		levelRepository.deleteByIdIn(ids);
	}

	public List<OrganizationPositionLevelRelation> getChildren(OrganizationPositionLevelPK parentId) {
		return organizationLevelRelationRepository.findByIdParentId(parentId);
	}
	
	public List<OrganizationPositionLevelRelation> getParents(OrganizationPositionLevelPK id) {
		return organizationLevelRelationRepository.findByIdId(id);
	}

	public List<Organization> getParentOrganizations(@NotBlank String positionLevelId) {
		List<Organization> organizations = new ArrayList<Organization>();
		organizationLevelRepository.findByIdPositionLevelId(positionLevelId).forEach(f -> {
			organizations.add(f.getOrganization());
		});
		return organizations;
	}
}

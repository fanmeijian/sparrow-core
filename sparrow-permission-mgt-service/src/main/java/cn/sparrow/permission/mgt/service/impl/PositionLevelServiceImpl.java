package cn.sparrow.permission.mgt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.mgt.api.PositionLevelService;
import cn.sparrow.permission.mgt.service.repository.EmployeeOrganizationLevelRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationLevelRelationRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationLevelRepository;
import cn.sparrow.permission.mgt.service.repository.PositionLevelRepository;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.EmployeeOrganizationLevel;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationPositionLevel;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelation;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelationPK;
import cn.sparrow.permission.model.organization.PositionLevel;

@Service
public class PositionLevelServiceImpl implements PositionLevelService{

	@Autowired
	PositionLevelRepository levelRepository;
	@Autowired
	OrganizationLevelRelationRepository organizationLevelRelationRepository;
	@Autowired
	OrganizationLevelRepository organizationLevelRepository;
	@Autowired EmployeeOrganizationLevelRepository employeeOrganizationLevelRepository;

	@Override
	public List<Employee> getEmployees(OrganizationPositionLevelPK organizationLevelId) {
		List<Employee> employees = new ArrayList<Employee>();
		employeeOrganizationLevelRepository.findByIdOrganizationLevelId(organizationLevelId).forEach(f->{
			employees.add(f.getEmployee());
		});
		return employees;
	}
	
	@Override
	@Transactional
	public PositionLevel create(PositionLevel lvel) {
		PositionLevel savedLevel = levelRepository.save(lvel);
		// 保存岗位所在的组织
		if (lvel.getOrganizationIds() != null) {
			lvel.getOrganizationIds().forEach(f -> {
				organizationLevelRepository.save(new OrganizationPositionLevel(new OrganizationPositionLevelPK(f, savedLevel.getId())));
			});
		}
		return savedLevel;
	}

	@Override
	@Transactional
	public void addRelation(List<OrganizationPositionLevelRelation> organizationLevelRelations) {
		organizationLevelRelationRepository.saveAll(organizationLevelRelations);
	}

	@Override
	@Transactional
	public void delete(String[] ids) {
		levelRepository.deleteByIdIn(ids);
	}

	@Override
	public List<OrganizationPositionLevel> getChildren(OrganizationPositionLevelPK parentId) {
		List<OrganizationPositionLevel> positionLevels = new ArrayList<OrganizationPositionLevel>();
		organizationLevelRelationRepository.findByIdParentId(parentId).forEach(f->{
			positionLevels.add(f.getOrganizationLevel());
		});
		return positionLevels;
	}
	
	@Override
	public List<OrganizationPositionLevel> getParents(OrganizationPositionLevelPK id) {
		List<OrganizationPositionLevel> positionLevels = new ArrayList<OrganizationPositionLevel>();
		organizationLevelRelationRepository.findByIdId(id).forEach(f->{
			positionLevels.add(f.getOrganizationLevel());
		});
		return positionLevels;
	}

	public List<Organization> getParentOrganizations(@NotBlank String positionLevelId) {
		List<Organization> organizations = new ArrayList<Organization>();
		organizationLevelRepository.findByIdPositionLevelId(positionLevelId).forEach(f -> {
			organizations.add(f.getOrganization());
		});
		return organizations;
	}


	@Override
	@Transactional
	public void removeRelation(List<OrganizationPositionLevelRelationPK> ids) {
		organizationLevelRelationRepository.deleteAllByIdInBatch(ids);;
	}

	@Override
	@Transactional
	public PositionLevel update(String positionLevelId, Map<String, Object> map) {
		PositionLevel source = levelRepository.getById(positionLevelId);
		PatchUpdateHelper.merge(source, map);
		return levelRepository.save(source);
	}

}

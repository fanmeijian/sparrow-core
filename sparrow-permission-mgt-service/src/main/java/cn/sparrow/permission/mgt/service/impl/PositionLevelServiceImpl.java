package cn.sparrow.permission.mgt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.sparrow.permission.mgt.api.PositionLevelService;
import cn.sparrow.permission.mgt.service.repository.EmployeeOrganizationLevelRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationLevelRelationRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationLevelRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationRepository;
import cn.sparrow.permission.mgt.service.repository.PositionLevelRepository;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationPositionLevel;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelation;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelationPK;
import cn.sparrow.permission.model.organization.PositionLevel;

@Service
public class PositionLevelServiceImpl implements PositionLevelService {

	@Autowired
	PositionLevelRepository levelRepository;
	@Autowired
	OrganizationLevelRelationRepository organizationLevelRelationRepository;
	@Autowired
	OrganizationLevelRepository organizationLevelRepository;
	@Autowired
	EmployeeOrganizationLevelRepository employeeOrganizationLevelRepository;
	@Autowired
	OrganizationRepository organizationRepository;

	@Override
	public List<Employee> getEmployees(OrganizationPositionLevelPK organizationLevelId) {
		List<Employee> employees = new ArrayList<Employee>();
		employeeOrganizationLevelRepository.findByIdOrganizationLevelId(organizationLevelId).forEach(f -> {
			employees.add(f.getEmployee());
		});
		return employees;
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.CREATED)
	public PositionLevel create(PositionLevel lvel) {
		return levelRepository.save(lvel);
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void addRelation(List<OrganizationPositionLevelRelation> organizationLevelRelations) {
		organizationLevelRelationRepository.saveAll(organizationLevelRelations);
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(String[] ids) {
		levelRepository.deleteByIdIn(ids);
	}

	@Override
	public List<OrganizationPositionLevel> getChildren(OrganizationPositionLevelPK parentId) {
		List<OrganizationPositionLevel> positionLevels = new ArrayList<OrganizationPositionLevel>();
		organizationLevelRelationRepository.findByIdParentId(parentId).forEach(f -> {
			positionLevels.add(f.getOrganizationLevel());
		});
		return positionLevels;
	}

	@Override
	public List<OrganizationPositionLevel> getParents(OrganizationPositionLevelPK id) {
		List<OrganizationPositionLevel> positionLevels = new ArrayList<OrganizationPositionLevel>();
		organizationLevelRelationRepository.findByIdId(id).forEach(f -> {
			positionLevels.add(organizationLevelRepository.findById(f.getId().getParentId()).get());
		});
		return positionLevels;
	}

	@Override
	public List<Organization> getParentOrganizations(@NotBlank String positionLevelId) {
		List<Organization> organizations = new ArrayList<Organization>();
		organizationLevelRepository.findByIdPositionLevelId(positionLevelId).forEach(f -> {
			organizations.add(organizationRepository.findById(f.getId().getOrganizationId()).get());
		});
		return organizations;
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeRelation(List<OrganizationPositionLevelRelationPK> ids) {
		organizationLevelRelationRepository.deleteAllByIdInBatch(ids);
		;
	}

	@Override
	@Transactional
	public PositionLevel update(String positionLevelId, Map<String, Object> map) {
		PositionLevel source = levelRepository.getById(positionLevelId);
		PatchUpdateHelper.merge(source, map);
		return levelRepository.save(source);
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void setParentOrg(String positionLevelId, List<String> orgs) {
		orgs.forEach(f -> {
			organizationLevelRepository.save(new OrganizationPositionLevel(f, positionLevelId));
		});
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeParentOrg(String positionLevelId, List<String> orgs) {
		orgs.forEach(f -> {
			organizationLevelRepository.deleteById(new OrganizationPositionLevelPK(f, positionLevelId));
		});
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void addRelation(OrganizationPositionLevelPK organizationLevelId, List<OrganizationPositionLevelPK> ids) {
		ids.forEach(f -> {
			organizationLevelRelationRepository.save(new OrganizationPositionLevelRelation(organizationLevelId, f));
		});
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeRelation(OrganizationPositionLevelPK organizationLevelId, List<OrganizationPositionLevelPK> ids) {
		ids.forEach(f -> {
			organizationLevelRelationRepository
					.deleteById(new OrganizationPositionLevelRelationPK(organizationLevelId, f));
		});
	}

	@Override
	public PositionLevel get(String positionLevelId) {
		return levelRepository.findById(positionLevelId).orElse(null);
	}

}

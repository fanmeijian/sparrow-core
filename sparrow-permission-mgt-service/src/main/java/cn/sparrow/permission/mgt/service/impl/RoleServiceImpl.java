package cn.sparrow.permission.mgt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.sparrow.permission.mgt.api.RoleService;
import cn.sparrow.permission.mgt.service.repository.EmployeeOrganizationRoleRepository;
import cn.sparrow.permission.mgt.service.repository.EmployeeRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationRoleRelationRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationRoleRepository;
import cn.sparrow.permission.mgt.service.repository.RoleRepository;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.OrganizationRole;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import cn.sparrow.permission.model.organization.OrganizationRoleRelation;
import cn.sparrow.permission.model.organization.OrganizationRoleRelationPK;
import cn.sparrow.permission.model.organization.Role;
import cn.sparrow.permission.model.organization.Role_;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	EmployeeOrganizationRoleRepository employeeOrganizationRoleRepository;
	@Autowired
	OrganizationRoleRepository organizationRoleRepository;
	@Autowired
	OrganizationRoleRelationRepository organizationRoleRelationRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	OrganizationRepository organizationRepository;

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.CREATED)
	public Role create(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public List<OrganizationRole> getParentOrganizations(@NotBlank String roleId) {
		return organizationRoleRepository.findByIdRoleId(roleId);
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@NotNull String[] ids) {
		roleRepository.deleteByIdIn(ids);
	}

	@Override
	public Role update(String roleId, Map<String, Object> map) {
		Role source = roleRepository.getById(roleId);
		PatchUpdateHelper.merge(source, map);
		return roleRepository.save(source);
	}

	@Override
	public List<OrganizationRoleRelation> getChildren(String organizationId, String roleId) {
		return getChildren(new OrganizationRolePK(organizationId, roleId));
	}

	@Override
	public List<OrganizationRoleRelation> getParents(String organizationId, String roleId) {
		return getParents(new OrganizationRolePK(organizationId, roleId));
	}

	public List<OrganizationRoleRelation> getChildren(@NotNull OrganizationRolePK parentId) {
		return organizationRoleRelationRepository.findByIdParentId(parentId);
	}

	public List<OrganizationRoleRelation> getParents(@NotNull OrganizationRolePK organizationRolePK) {
		return organizationRoleRelationRepository.findByIdId(organizationRolePK);
	}

	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void addRelations(List<OrganizationRoleRelationPK> ids) {
		ids.forEach(f -> {
			organizationRoleRelationRepository.save(new OrganizationRoleRelation(f));
		});
	}

	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delRelations(List<OrganizationRoleRelationPK> ids) {
		ids.forEach(f -> {
			organizationRoleRelationRepository.deleteById(f);
		});
	}

	@Override
	public Page<Role> all(Pageable pageable, Role role) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths(Role_.IS_ROOT).withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return roleRepository.findAll(Example.of(role, matcher), pageable);
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void setParentOrg(String roleId, List<String> orgs) {
		orgs.forEach(f -> {
			organizationRoleRepository.save(new OrganizationRole(f, roleId));
		});
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeParentOrg(String roleId, List<String> orgs) {
		orgs.forEach(f -> {
			organizationRoleRepository.deleteById(new OrganizationRolePK(f, roleId));
		});
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void addParents(OrganizationRolePK organizationRoleId, @NotNull List<OrganizationRolePK> ids) {
		ids.forEach(f -> {
			organizationRoleRelationRepository.save(new OrganizationRoleRelation(organizationRoleId, f));
		});
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delParents(OrganizationRolePK organizationRoleId, @NotNull List<OrganizationRolePK> ids) {
		ids.forEach(f -> {
			organizationRoleRelationRepository.deleteById(new OrganizationRoleRelationPK(organizationRoleId, f));
		});
	}

	@Override
	public List<Employee> getEmployees(OrganizationRolePK organizationRoleId) {
		List<Employee> employees = new ArrayList<Employee>();
		employeeOrganizationRoleRepository.findByIdOrganizationRoleId(organizationRoleId).forEach(f -> {
			employees.add(employeeRepository.findById(f.getId().getEmployeeId()).get());
		});
		return employees;
	}

	@Override
	public Role get(String roleId) {
		return roleRepository.findById(roleId).get();
	}

}

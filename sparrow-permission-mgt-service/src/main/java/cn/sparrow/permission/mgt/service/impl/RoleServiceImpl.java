package cn.sparrow.permission.mgt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.mgt.api.RoleService;
import cn.sparrow.permission.mgt.service.repository.EmployeeOrganizationRoleRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationRoleRelationRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationRoleRepository;
import cn.sparrow.permission.mgt.service.repository.RoleRepository;
import cn.sparrow.permission.model.organization.EmployeeOrganizationRole;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationRole;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import cn.sparrow.permission.model.organization.OrganizationRoleRelation;
import cn.sparrow.permission.model.organization.OrganizationRoleRelationPK;
import cn.sparrow.permission.model.organization.Role;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	EmployeeOrganizationRoleRepository employeeOrganizationRoleRepository;
	@Autowired
	OrganizationRoleRepository organizationRoleRepository;
	@Autowired
	OrganizationRoleRelationRepository organizationRoleRelationRepository;

	
	@Override
	@Transactional
	public Role create(Role role) {
		Role savedRole = roleRepository.save(role);
		// 保存岗位所在的组织
		if (role.getOrganizationIds() != null) {
			role.getOrganizationIds().forEach(f -> {
				OrganizationRole organizationRole = organizationRoleRepository
						.save(new OrganizationRole(new OrganizationRolePK(f, savedRole.getId())));
				// 保存岗位之间的关系,注意这里的岗位关系指的是组织岗位之间的关系，因为岗位独立于组织没有意义
				if (role.getParentIds() != null) {
					role.getParentIds().forEach(parentId -> {
						organizationRoleRelationRepository.save(new OrganizationRoleRelation(
								new OrganizationRoleRelationPK(organizationRole.getId(), parentId)));
					});
				}
			});
		}
		return savedRole;
	}

	@Override
	public List<Organization> getParentOrganizations(@NotBlank String roleId) {
		List<Organization> organizations = new ArrayList<Organization>();
		organizationRoleRepository.findByIdRoleId(roleId).forEach(f -> {
			organizations.add(f.getOrganization());
		});
		return organizations;
	}

	@Override
	@Transactional
	public void delete(@NotNull String[] ids) {
		roleRepository.deleteByIdIn(ids);
	}

	@Override
	public Role update(String roleId, Map<String,Object> map) {
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

	public List<EmployeeOrganizationRole> getEmployees(OrganizationRolePK organizationRoleId) {
		return employeeOrganizationRoleRepository.findByIdOrganizationRoleId(organizationRoleId);
	}

	public List<OrganizationRoleRelation> getChildren(@NotNull OrganizationRolePK parentId) {
		return organizationRoleRelationRepository.findByIdParentId(parentId);
	}

	public List<OrganizationRoleRelation> getParents(@NotNull OrganizationRolePK organizationRolePK) {
		return organizationRoleRelationRepository.findByIdId(organizationRolePK);
	}

	@Transactional
	public void addRelations(List<OrganizationRoleRelationPK> ids) {
		ids.forEach(f -> {
			organizationRoleRelationRepository.save(new OrganizationRoleRelation(f));
		});
	}

	@Transactional
	public void delRelations(List<OrganizationRoleRelationPK> ids) {
		ids.forEach(f -> {
			organizationRoleRelationRepository.deleteById(f);
		});
	}

	@Override
	public Page<Role> all(Pageable pageable, Role role) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return roleRepository.findAll(Example.of(role, matcher), pageable);
	}

}

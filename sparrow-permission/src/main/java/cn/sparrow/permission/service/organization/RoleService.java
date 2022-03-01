package cn.sparrow.permission.service.organization;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.organization.EmployeeOrganizationRole;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationRole;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import cn.sparrow.permission.model.organization.OrganizationRoleRelation;
import cn.sparrow.permission.model.organization.OrganizationRoleRelationPK;
import cn.sparrow.permission.model.organization.Role;
import cn.sparrow.permission.repository.organization.EmployeeOrganizationRoleRepository;
import cn.sparrow.permission.repository.organization.OrganizationRoleRelationRepository;
import cn.sparrow.permission.repository.organization.OrganizationRoleRepository;
import cn.sparrow.permission.repository.organization.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	EmployeeOrganizationRoleRepository employeeOrganizationRoleRepository;
	@Autowired
	OrganizationRoleRepository organizationRoleRepository;
	@Autowired
	OrganizationRoleRelationRepository organizationRoleRelationRepository;

	public List<EmployeeOrganizationRole> getEmployees(OrganizationRolePK organizationRoleId) {
		return employeeOrganizationRoleRepository.findByIdOrganizationRoleId(organizationRoleId);
	}

	public List<OrganizationRoleRelation> getChildren(@NotNull OrganizationRolePK parentId) {
		return organizationRoleRelationRepository.findByIdParentId(parentId);
	}

	public List<OrganizationRoleRelation> getParents(@NotNull OrganizationRolePK organizationRolePK) {
		return organizationRoleRelationRepository.findByIdId(organizationRolePK);
	}

	public void addRelations(List<OrganizationRoleRelationPK> ids) {
		ids.forEach(f -> {
			organizationRoleRelationRepository.save(new OrganizationRoleRelation(f));
		});
	}

	public void delRelations(List<OrganizationRoleRelationPK> ids) {
		ids.forEach(f -> {
			organizationRoleRelationRepository.deleteById(f);
		});
	}

	@Transactional
	public Role save(Role role) {
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

	@Transactional
	public void delBatch(String[] ids) {
		roleRepository.deleteByIdIn(ids);
	}

	public List<Organization> getParentOrganizations(@NotBlank String roleId) {
		List<Organization> organizations = new ArrayList<Organization>();
		organizationRoleRepository.findByIdRoleId(roleId).forEach(f -> {
			organizations.add(f.getOrganization());
		});
		return organizations;
	}

}

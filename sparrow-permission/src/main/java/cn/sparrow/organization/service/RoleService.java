package cn.sparrow.organization.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.organization.EmployeeOrganizationRole;
import cn.sparrow.model.organization.OrganizationRole;
import cn.sparrow.model.organization.OrganizationRolePK;
import cn.sparrow.model.organization.OrganizationRoleRelation;
import cn.sparrow.model.organization.OrganizationRoleRelationPK;
import cn.sparrow.model.organization.Role;
import cn.sparrow.organization.repository.EmployeeOrganizationRoleRepository;
import cn.sparrow.organization.repository.OrganizationRoleRelationRepository;
import cn.sparrow.organization.repository.OrganizationRoleRepository;
import cn.sparrow.organization.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	@Autowired EmployeeOrganizationRoleRepository employeeOrganizationRoleRepository;
	@Autowired
	OrganizationRoleRepository organizationRoleRepository;
	@Autowired
	OrganizationRoleRelationRepository organizationRoleRelationRepository;

	public List<EmployeeOrganizationRole> getEmployees(OrganizationRolePK organizationRoleId){
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

}

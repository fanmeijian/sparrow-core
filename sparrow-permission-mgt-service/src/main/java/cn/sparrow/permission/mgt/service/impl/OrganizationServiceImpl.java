package cn.sparrow.permission.mgt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.sparrow.permission.constant.OrganizationChildTypeEnum;
import cn.sparrow.permission.core.api.ScopePermission;
import cn.sparrow.permission.mgt.api.EmployeeService;
import cn.sparrow.permission.mgt.api.GroupService;
import cn.sparrow.permission.mgt.api.OrganizationService;
import cn.sparrow.permission.mgt.api.PositionLevelService;
import cn.sparrow.permission.mgt.api.RoleService;
import cn.sparrow.permission.mgt.api.scopes.OrgScope;
import cn.sparrow.permission.mgt.service.repository.EmployeeRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationGroupRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationLevelRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationPositionLevelRelationRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationRelationRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationRoleRelationRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationRoleRepository;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationGroup;
import cn.sparrow.permission.model.organization.OrganizationGroupPK;
import cn.sparrow.permission.model.organization.OrganizationPositionLevel;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationRelation;
import cn.sparrow.permission.model.organization.OrganizationRelationPK;
import cn.sparrow.permission.model.organization.OrganizationRole;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import cn.sparrow.permission.model.resource.SparrowTree;

@Service
public class OrganizationServiceImpl extends AbstractPreserveScope implements OrganizationService, OrgScope {

	@Autowired
	OrganizationRelationRepository organizationRelationRepository;
	@Autowired
	OrganizationRoleRepository organizationRoleRepository;
	@Autowired
	OrganizationRoleRelationRepository organizationRoleRelationRepository;
	@Autowired
	OrganizationLevelRepository organizationLevelRepository;
	@Autowired
	OrganizationPositionLevelRelationRepository organizationPositionLevelRelationRepository;
	@Autowired
	OrganizationGroupRepository organizationGroupRepository;
	@Autowired
	OrganizationRepository organizationRepository;
	@Autowired
	RoleService roleService;
	@Autowired
	PositionLevelService levelService;
	@Autowired
	GroupService groupService;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EmployeeService employeeService;

	@Override
	public Page<OrganizationGroup> getGroups(String organizationId, Pageable pageable) {
		return organizationGroupRepository.findByIdOrganizationId(organizationId, pageable);
	}

	@Override
	public Page<OrganizationRole> getRoles(String organizationId, Pageable pageable) {
		return organizationRoleRepository.findByIdOrganizationId(organizationId, pageable);
	}

	@Override
	public Page<OrganizationPositionLevel> getLevels(String organizationId, Pageable pageable) {
		return organizationLevelRepository
				.findByIdOrganizationId(organizationId, pageable);
	}

	@Override
	public Page<Employee> getEmployees(@NotBlank String organizationId, Pageable pageable) {
		return employeeRepository.findByOrganizationId(organizationId, pageable);
	}

	@Override
	public List<Organization> getChildren(String parentId) {
		List<OrganizationRelation> organizationRelations = new ArrayList<OrganizationRelation>();
		if (parentId == null || parentId.isBlank()) {
			organizationRepository.findByIsRoot(true).forEach(f -> {
				organizationRelations.add(new OrganizationRelation(f));
			});
		} else {
			organizationRelations.addAll(organizationRelationRepository.findByIdParentId(parentId));
		}

		List<Organization> children = new ArrayList<Organization>();
		organizationRelations.forEach(f -> {
			Organization organization = organizationRepository.findById(f.getId().getOrganizationId()).get();
			children.add(organization);
		});
		return children;
	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_"+SCOPE_ADMIN_PARENT_LIST+"') or hasRole('ROLE_"+ROLE_ADMIN+"')")
	public List<OrganizationRelation> getParents(String organizationId) {
		return organizationRelationRepository.findByIdOrganizationId(organizationId);
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('SCOPE_"+SCOPE_ADMIN_DELETE+"') or hasRole('ROLE_"+ROLE_SUPER_ADMIN+"')")
	public void delete(String[] ids) {
		organizationRelationRepository.deleteByIdOrganizationIdInOrIdParentIdIn(ids, ids);
		organizationRepository.deleteByIdIn(ids);
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('SCOPE_"+SCOPE_ADMIN_PARENT_ADD+"') or hasRole('ROLE_"+ROLE_ADMIN+"')")
	public void addParent(String organizationId, List<String> parentIds) {

		parentIds.forEach(f -> {
			if (f.equals("root")) {
				Organization organization = organizationRepository.getById(organizationId);
				organization.setIsRoot(true);
				organizationRepository.save(organization);
			} else {
				organizationRelationRepository
						.save(new OrganizationRelation(new OrganizationRelationPK(organizationId, f)));
			}
		});

	}

	public void addRoles(Set<OrganizationRolePK> ids) {
		ids.forEach(f -> {
			organizationRoleRepository.saveAndFlush(new OrganizationRole(f));
		});
	}

	public void delRoles(Set<OrganizationRolePK> ids) {
		ids.forEach(f -> {
			organizationRoleRepository.delete(new OrganizationRole(f));
		});
	}

	public void addLevels(Set<OrganizationPositionLevelPK> ids) {
		ids.forEach(f -> {
			organizationLevelRepository.saveAndFlush(new OrganizationPositionLevel(f));
		});
	}

	public void delLevels(Set<OrganizationPositionLevelPK> ids) {
		ids.forEach(f -> {
			organizationLevelRepository.delete(new OrganizationPositionLevel(f));
		});
	}

	public void addGroups(Set<OrganizationGroupPK> ids) {
		ids.forEach(f -> {
			organizationGroupRepository.saveAndFlush(new OrganizationGroup(f));
		});
	}

	public void delGroups(Set<OrganizationGroupPK> ids) {
		ids.forEach(f -> {
			organizationGroupRepository.delete(new OrganizationGroup(f));
		});
	}

	@Override
	public SparrowTree<Organization, String> getTreeByParentId(String parentId) {

		if (parentId == null) {
			SparrowTree<Organization, String> rootTree = new SparrowTree<Organization, String>(null);
			organizationRepository.findByIsRoot(true).forEach(f -> {
				SparrowTree<Organization, String> myTree = new SparrowTree<Organization, String>(f);
				buildTree(myTree);
				rootTree.getChildren().add(myTree);
			});

			return rootTree;
		} else {
			SparrowTree<Organization, String> myTree = new SparrowTree<Organization, String>(
					organizationRepository.findById(parentId).orElse(null));
			buildTree(myTree);
			return myTree;
		}

	}

	public void buildTree(SparrowTree<Organization, String> myTree) {
		organizationRelationRepository.findByIdParentId(myTree.getMe() == null ? null : myTree.getMe().getId())
				.forEach(f -> {
					Organization organization = organizationRepository.findById(f.getId().getOrganizationId()).get();
					SparrowTree<Organization, String> leaf = new SparrowTree<Organization, String>(organization);
					// 防止死循环
					if (organizationRelationRepository
							.findById(
									new OrganizationRelationPK(f.getId().getParentId(), f.getId().getOrganizationId()))
							.orElse(null) == null)
						buildTree(leaf);
					myTree.getChildren().add(leaf);
				});
	}

	@Override
	@ResponseStatus(code = HttpStatus.CREATED)
	@ScopePermission(scope = SCOPE_ADMIN_CREATE)
	@PreAuthorize("hasAuthority('SCOPE_"+SCOPE_ADMIN_CREATE+"') or hasRole('ROLE_"+ROLE_ADMIN+"')")
	public Organization create(@Valid Organization organization) {
		return organizationRepository.save(organization);
	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_"+SCOPE_ADMIN_UPDATE+"') or hasRole('ROLE_"+ROLE_ADMIN+"')")
	public Organization update(String id, Map<String, Object> map) {
		Organization source = organizationRepository.getById(id);
		PatchUpdateHelper.merge(source, map);
		return organizationRepository.save(source);
	}

	@Override
	@Transactional
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('SCOPE_"+SCOPE_ADMIN_PARENT_REMOVE+"') or hasRole('ROLE_"+ROLE_SUPER_ADMIN+"')")
	public void removeParent(String organizationId, List<String> parentIds) {
		parentIds.forEach(f -> {
			if (f.equals("root")) {
				Organization organization = organizationRepository.getById(organizationId);
				organization.setIsRoot(false);
				organizationRepository.save(organization);
			} else {
				organizationRelationRepository.deleteById(new OrganizationRelationPK(organizationId, f));
			}
		});

		// 防止称为孤儿
		if (organizationRelationRepository.findByIdOrganizationId(organizationId).size() == 0) {
			Organization organization = organizationRepository.getById(organizationId);
			organization.setIsRoot(true);
			organizationRepository.save(organization);
		}

	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_"+SCOPE_ADMIN_READ+"') or hasRole('ROLE_"+ROLE_ADMIN+"')")
	public Organization get(String organizationId) {
		return organizationRepository.findById(organizationId).get();
	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_"+SCOPE_ADMIN_CHILD_LIST+"') or hasRole('ROLE_"+ROLE_ADMIN+"')")
	public Page<?> getChildren(String organizationId, OrganizationChildTypeEnum type, Pageable pageable) {

		switch (type) {
		case ORGANIZATION:
			if (organizationId.toLowerCase().equals("root")) {
				return organizationRepository.findByIsRoot(true, Pageable.unpaged());
			} else {
				return organizationRelationRepository.findByIdParentId(organizationId, pageable);
			}
		case ROLE:
			return organizationRoleRepository.findByIdOrganizationId(organizationId, pageable);
		case LEVEL:
			return organizationLevelRepository.findByIdOrganizationId(organizationId, pageable);
		case GROUP:
			return organizationGroupRepository.findByIdOrganizationId(organizationId, pageable);
		case EMPLOYEE:
			return employeeRepository.findAllByOrganizationId(organizationId, pageable);
		default:
			break;
		}
		return null;
	}

}

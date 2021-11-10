package cn.sparrow.organization.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.common.service.GroupService;
import cn.sparrow.model.common.SparrowTree;
import cn.sparrow.model.organization.Employee;
import cn.sparrow.model.organization.Organization;
import cn.sparrow.model.organization.OrganizationGroup;
import cn.sparrow.model.organization.OrganizationGroupPK;
import cn.sparrow.model.organization.OrganizationPositionLevel;
import cn.sparrow.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.model.organization.OrganizationRelation;
import cn.sparrow.model.organization.OrganizationRelationPK;
import cn.sparrow.model.organization.OrganizationRole;
import cn.sparrow.model.organization.OrganizationRolePK;
import cn.sparrow.organization.repository.EmployeeRepository;
import cn.sparrow.organization.repository.OrganizationGroupRepository;
import cn.sparrow.organization.repository.OrganizationLevelRepository;
import cn.sparrow.organization.repository.OrganizationPositionLevelRelationRepository;
import cn.sparrow.organization.repository.OrganizationRelationRepository;
import cn.sparrow.organization.repository.OrganizationRepository;
import cn.sparrow.organization.repository.OrganizationRoleRelationRepository;
import cn.sparrow.organization.repository.OrganizationRoleRepository;

@Service
public class OrganizationService {

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

	public Organization add(Organization organization) {
		Organization org = organizationRepository.save(organization);
		// if (organization.getParentIds() != null) {
		// organization.getParentIds().forEach(f -> {
		// organizationRelationRepository
		// .save(new OrganizationRelation(new OrganizationRelationPK(org.getId(), f)));
		// });
		// }
		return org;
	}

	public List<OrganizationGroup> getOrganizationGroups(@NotBlank String organizationId) {
		return organizationGroupRepository.findByIdOrganizationId(organizationId);
	}

	public List<OrganizationRole> getOrganizationRoles(@NotBlank String organizationId) {
		List<OrganizationRole> roles = organizationRoleRepository.findByIdOrganizationId(organizationId);
		roles.forEach(f -> {
			f.setChildCount(organizationRoleRelationRepository.countByIdParentId(f.getId()));
		});
		return roles;
	}

	public List<OrganizationPositionLevel> getOrganizationLevels(@NotBlank String organizationId) {
		List<OrganizationPositionLevel> organizationPositionLevels = organizationLevelRepository
				.findByIdOrganizationId(organizationId);
		organizationPositionLevels.forEach(f -> {
			f.setChildCount(organizationPositionLevelRelationRepository.countByIdParentId(f.getId()));
		});
		return organizationPositionLevels;
	}

	public List<Employee> getEmployees(@NotBlank String organizationId, Pageable pageable) {
		List<Employee> employees = employeeRepository.findByOrganizationId(organizationId, pageable);
		employees.forEach(f -> {
			f.setChildCount(employeeService.getChildCount(f.getId()));
		});
		return employees;
	}

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
			f.getOrganization().setParentCount(
					organizationRelationRepository.countByIdOrganizationId(f.getOrganization().getId()));
			f.getOrganization()
					.setChildCount(organizationRelationRepository.countByIdParentId(f.getOrganization().getId()));
			f.getOrganization()
					.setRoleCount(organizationRoleRepository.countByIdOrganizationId(f.getOrganization().getId()));
			f.getOrganization()
					.setLevelCount(organizationLevelRepository.countByIdOrganizationId(f.getOrganization().getId()));
			f.getOrganization()
					.setGroupCount(organizationGroupRepository.countByIdOrganizationId(f.getOrganization().getId()));
			f.getOrganization().setEmployeeCount(employeeRepository.countByOrganizationId(f.getOrganization().getId()));
			children.add(f.getOrganization());
		});
		return children;
	}

	public List<Organization> getParents(String organizationId) {
		List<Organization> parents = new ArrayList<Organization>();
		organizationRelationRepository.findByIdOrganizationId(organizationId).forEach(f -> {
			parents.add(f.getParent());
		});
		return parents;
	}

	@Transactional
	public void delBatch(String[] ids) {
		organizationRelationRepository.deleteByIdOrganizationIdInOrIdParentIdIn(ids, ids);
		organizationRepository.deleteByIdIn(ids);
	}

	@Transactional
	public void addRelations(Set<OrganizationRelation> organizationRelations) {
		organizationRelations.forEach(f -> {
			if (f.getId().getParentId().equals("-1")) {
				Organization rootOrganization = organizationRepository.getOne(f.getId().getOrganizationId());
				rootOrganization.setIsRoot(true);
				organizationRepository.save(rootOrganization);
			} else {
				organizationRelationRepository.save(f);
			}
		});

	}

	public void removeRelations(Set<OrganizationRelationPK> ids) {
		organizationRelationRepository.deleteByIdIn(ids);
	}

	@Transactional
	public void updateParent(Set<OrganizationRelation> organizationRelations) {

		// if there's no parent, do not allow to remove relation.
		Map<String, Boolean> mapIsRoot = new HashMap<String, Boolean>();
		Map<String, Boolean> mapHasParent = new HashMap<String, Boolean>();

		organizationRelations.forEach(f -> {
			if (f.getId().getParentId().equals("-1")) {
				mapIsRoot.put(f.getId().getOrganizationId(), true);
			} else {
				mapHasParent.put(f.getId().getOrganizationId(), true);
			}
		});

		organizationRelations.forEach(f -> {
			if (mapIsRoot.containsKey(f.getId().getOrganizationId())
					|| mapHasParent.containsKey(f.getId().getOrganizationId())) {
				organizationRelationRepository.deleteByIdOrganizationId(f.getId().getOrganizationId());
			}
		});

		// set non root organization
		mapHasParent.forEach((k, v) -> {
			if (!mapIsRoot.containsKey(k)) {
				Organization rootOrganization = organizationRepository.getOne(k);
				rootOrganization.setIsRoot(false);
				organizationRepository.save(rootOrganization);
			}
		});
		addRelations(organizationRelations);
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

	public SparrowTree<Organization, String> getTree(String parentId) {

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
					SparrowTree<Organization, String> leaf = new SparrowTree<Organization, String>(f.getOrganization());
					// 防止死循环
					if (organizationRelationRepository
							.findById(
									new OrganizationRelationPK(f.getId().getParentId(), f.getId().getOrganizationId()))
							.orElse(null) == null)
						buildTree(leaf);
					myTree.getChildren().add(leaf);
				});
	}

}

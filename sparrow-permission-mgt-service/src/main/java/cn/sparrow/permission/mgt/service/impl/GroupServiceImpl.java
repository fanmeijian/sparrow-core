package cn.sparrow.permission.mgt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.mgt.api.GroupService;
import cn.sparrow.permission.mgt.api.OrganizationService;
import cn.sparrow.permission.mgt.service.repository.GroupEmployeeRepository;
import cn.sparrow.permission.mgt.service.repository.GroupLevelRepository;
import cn.sparrow.permission.mgt.service.repository.GroupOrganizationRepository;
import cn.sparrow.permission.mgt.service.repository.GroupRelationRepository;
import cn.sparrow.permission.mgt.service.repository.GroupRepository;
import cn.sparrow.permission.mgt.service.repository.GroupRoleRepository;
import cn.sparrow.permission.mgt.service.repository.GroupSysroleRepository;
import cn.sparrow.permission.mgt.service.repository.GroupUserRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationGroupRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationRepository;
import cn.sparrow.permission.model.group.Group;
import cn.sparrow.permission.model.group.GroupMember;
import cn.sparrow.permission.model.group.GroupOrganization;
import cn.sparrow.permission.model.group.GroupOrganizationPK;
import cn.sparrow.permission.model.group.GroupPositionLevel;
import cn.sparrow.permission.model.group.GroupPositionLevelPK;
import cn.sparrow.permission.model.group.GroupRelation;
import cn.sparrow.permission.model.group.GroupRelationPK;
import cn.sparrow.permission.model.group.GroupRole;
import cn.sparrow.permission.model.group.GroupRolePK;
import cn.sparrow.permission.model.group.GroupSysrole;
import cn.sparrow.permission.model.group.GroupSysrolePK;
import cn.sparrow.permission.model.group.GroupUser;
import cn.sparrow.permission.model.group.GroupUserPK;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationGroup;
import cn.sparrow.permission.model.organization.OrganizationGroupPK;
import cn.sparrow.permission.model.resource.SparrowTree;

/**
 * 群组服务
 * 
 * @author fanmj
 *
 */

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupUserRepository groupUserRepository;
	@Autowired
	GroupOrganizationRepository groupOrganizationRepository;
	@Autowired
	GroupRoleRepository groupRoleRepository;
	@Autowired
	GroupLevelRepository groupLevelRepository;
	@Autowired
	GroupRelationRepository groupRelationRepository;
	@Autowired
	GroupSysroleRepository groupSysroleRepository;
	
	@Autowired OrganizationGroupRepository organizationGroupRepository;
	@Autowired GroupEmployeeRepository groupEmployeeRepository;

	// @Autowired
	// SubGroupRepository subGroupRepository;

	@Autowired
	GroupRepository groupRepository;
	@Autowired OrganizationRepository organizationRepository;

	// @Override
	// public void addRelations(Set<GroupRelationPK> ids) {
	// 	ids.forEach(f->{
	// 		groupRelationRepository.save(new GroupRelation(f));
	// 	});
	// }

	// @Override
	// public void delRelations(Set<GroupRelationPK> ids) {
	// 	ids.forEach(f -> {
	// 		groupRelationRepository.deleteById(f);
	// 	});
	// }

	// @Override
	// public void addOrganizations(Set<GroupOrganizationPK> ids) {
	// 	ids.forEach(f -> {
	// 		groupOrganizationRepository.save(new GroupOrganization(f));
	// 	});
	// }

	// @Override
	// public void delOrganizations(Set<GroupOrganizationPK> ids) {
	// 	ids.forEach(f -> {
	// 		groupOrganizationRepository.delete(new GroupOrganization(f));
	// 	});
	// }

	// @Override
	// public void addRoles(Set<GroupRolePK> ids) {
	// 	ids.forEach(f -> {
	// 		groupRoleRepository.save(new GroupRole(f));
	// 	});
	// }

	// @Override
	// public void delRoles(Set<GroupRolePK> ids) {
	// 	ids.forEach(f -> {
	// 		groupRoleRepository.delete(new GroupRole(f));
	// 	});
	// }

	// @Override
	// public void addLevels(Set<GroupPositionLevelPK> ids) {
	// 	ids.forEach(f -> {
	// 		groupLevelRepository.save(new GroupPositionLevel(f));
	// 	});
	// }

	// @Override
	// public void delLevels(Set<GroupPositionLevelPK> ids) {
	// 	ids.forEach(f -> {
	// 		groupLevelRepository.delete(new GroupPositionLevel(f));
	// 	});
	// }

	// @Override
	// public void addSysroles(Set<GroupSysrolePK> ids) {
	// 	ids.forEach(f -> {
	// 		groupSysroleRepository.save(new GroupSysrole(f));
	// 	});
	// }

	// @Override
	// public void delSysroles(Set<GroupSysrolePK> ids) {
	// 	ids.forEach(f -> {
	// 		groupSysroleRepository.delete(new GroupSysrole(f));
	// 	});
	// }

	// @Override
	// public void addUsers(Set<GroupUserPK> ids) {
	// 	ids.forEach(f -> {
	// 		groupUserRepository.save(new GroupUser(f));
	// 	});
	// }

	// @Override
	// public void delUsers(Set<GroupUserPK> ids) {
	// 	ids.forEach(f -> {
	// 		groupUserRepository.delete(new GroupUser(f));
	// 	});
	// }

	@Override
	public SparrowTree<Group, String> getTree(String parentId) {
		SparrowTree<Group, String> myTree = new SparrowTree<Group, String>(
				parentId == null ? null : groupRepository.findById(parentId).orElse(null));
		buildTree(myTree);
		return myTree;
	}

	
	public void buildTree(SparrowTree<Group, String> myTree) {
		groupRelationRepository.findByIdParentId(myTree.getMe() == null ? null : myTree.getMe().getId()).forEach(f -> {
			SparrowTree<Group, String> leaf = new SparrowTree<Group, String>(f.getGroup());
			// 防止死循环
			if (groupRelationRepository.findById(new GroupRelationPK(f.getId().getParentId(), f.getId().getGroupId()))
					.orElse(null) == null)
				buildTree(leaf);
			myTree.getChildren().add(leaf);
		});
	}

	@Override
	public Group add(Group group) {
		Group savedGroup = groupRepository.save(group);
		// save relation
		// savedGroup.getOrganizationIds().forEach(f->{
		// 	organizationGroupRepository.save(new OrganizationGroup(new OrganizationGroupPK(f,savedGroup.getId())));
		// });
		return savedGroup;
	}

	@Transactional
	@Override
	public void addMembers(GroupMember groupMember) {
		if(groupMember.getGroupRelations()!=null) {
			// save sub group
			groupRelationRepository.saveAll(groupMember.getGroupRelations());
		}
		
		if(groupMember.getGroupOrganizations()!=null) {
			groupOrganizationRepository.saveAll(groupMember.getGroupOrganizations());
		}
		
		if(groupMember.getGroupRoles()!=null) {
			groupRoleRepository.saveAll(groupMember.getGroupRoles());
		}
		
		if(groupMember.getGroupLevels()!=null) {
			groupLevelRepository.saveAll(groupMember.getGroupLevels());
		}
		
		if(groupMember.getGroupSysroles()!=null) {
			groupSysroleRepository.saveAll(groupMember.getGroupSysroles());
		}
		
		if(groupMember.getGroupEmployees()!=null) {
			groupEmployeeRepository.saveAll(groupMember.getGroupEmployees());
		}
	}

	@Override
	public GroupMember getGroupMember(String groupId) {
		GroupMember groupMember = new GroupMember();
		groupMember.setGroupEmployees(groupEmployeeRepository.findByIdGroupId(groupId));
		groupMember.setGroupRelations(groupRelationRepository.findByIdParentId(groupId));
		groupMember.setGroupOrganizations(groupOrganizationRepository.findByIdGroupId(groupId));
		groupMember.setGroupRoles(groupRoleRepository.findByIdGroupId(groupId));
		groupMember.setGroupLevels(groupLevelRepository.findByIdGroupId(groupId));
		groupMember.setGroupSysroles(groupSysroleRepository.findByIdGroupId(groupId));

		return groupMember;
	}

	@Override
	public List<Employee> getFinalEmployees(@NotBlank String groupId) {
		// get the actual employees from all group member except organization for organization member are too large
		
		return null;
	}

	@Override
	public Group update(String groupId, Map<String, Object> map) {
		Group source = groupRepository.getById(groupId);
		PatchUpdateHelper.merge(source, map);
		return groupRepository.save(source);
	}

	@Override
	public void delete(List<String> ids) {
		groupRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public Page<Group> all(Pageable pageable, Group group){
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return groupRepository.findAll(Example.of(group, matcher), pageable);
	}


	@Override
	public void removeMembers(GroupMember groupMember) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Organization> getParentOrgs(String groupId) {
		List<Organization> organizations = new ArrayList<>();
		organizationGroupRepository.findByIdGroupId(groupId).forEach(f->{
			organizations.add(organizationRepository.findById(f.getId().getOrganizationId()).get());
		});
		return organizations;
	}


	@Override
	@Transactional
	public void setParentOrgs(String groupId, List<String> orgs) {
		orgs.forEach(f->{
			organizationGroupRepository.save(new OrganizationGroup(f,groupId));
		});
	}


	@Override
	@Transactional
	public void removeParentOrgs(String groupId, List<String> orgs) {
		orgs.forEach(f->{
			organizationGroupRepository.deleteById(new OrganizationGroupPK(f, groupId));
		});
	}


//  public List<E> getGroupMembers() {
//	  
//  }

	/**
	 * 以树的形式返回
	 * 
	 * @param id
	 * @return
	 */
	// @GetMapping("/subGroups/tree")
	// public ApiResponse subGroupsRecursionTree(@RequestParam(name = "id") String
	// id) {
	// Map<String, SwdGroup> stopRecursion = new HashMap<String, SwdGroup>();// 防止递归
	// GroupExt groupExt = new GroupExt(groupRepository.findById(id).get());
	//// groupExt.getGroupUsers().addAll(groupUserRepository.findByIdGroupId(id,
	// Pageable.unpaged()).toList());
	// stopRecursion.put(groupExt.getId(), groupExt);
	// subGroupsTree(groupExt, stopRecursion);
	// return ApiResponseFactory.getNormalReponse(groupExt);
	// }
	//
	// private void subGroupsTree(GroupExt groupExt, Map<String, SwdGroup> map) {
	// for (GroupExt group : subGroupRepository.findByGroupId(groupExt.getId())) {
	// groupExt.getSubGroups().add(group);
	// if (!map.containsKey(group.getId())) {
	// map.put(group.getId(), group);
	// groupExt.getGroupUsers().addAll(groupUserRepository.findByIdGroupId(group.getId(),Pageable.unpaged()).toList());
	// subGroupsTree(group, map);
	// }
	// }
	// }

	/**
	 * 设置子组
	 * 
	 * @return
	 */
	// @PutMapping("/subGroups/add")
	// public ApiResponse subGroupsAdd(@RequestBody List<String> groups,
	// @RequestParam(name = "id")
	// String id) {
	// List<SwdSubGroup> subGroups = new ArrayList<SwdSubGroup>();
	// groups.forEach(group -> {
	// SwdSubGroup subGroup = new SwdSubGroup();
	// SwdSubGroupPK swdSubGroupPK = new SwdSubGroupPK();
	// swdSubGroupPK.setGroupId(id);
	// swdSubGroupPK.setSubGroupId(group);
	// subGroup.setId(swdSubGroupPK);
	// subGroups.add(subGroup);
	// });
	// subGroupRepository.saveAll(subGroups);
	// return ApiResponseFactory.getNormalReponse();
	// }

	/**
	 * 移除子组
	 * 
	 * @return
	 */
	// @PutMapping("/subGroups/remove")
	// public ApiResponse subGroupsRemove(@RequestBody List<String> groups,
	// @RequestParam(name = "id")
	// String id) {
	// List<SwdSubGroup> subGroups = new ArrayList<SwdSubGroup>();
	// groups.forEach(group -> {
	// SwdSubGroup subGroup = new SwdSubGroup();
	// SwdSubGroupPK swdSubGroupPK = new SwdSubGroupPK();
	// swdSubGroupPK.setGroupId(id);
	// swdSubGroupPK.setSubGroupId(group);
	// subGroup.setId(swdSubGroupPK);
	// subGroups.add(subGroup);
	// });
	// subGroupRepository.deleteAll(subGroups);
	// return ApiResponseFactory.getNormalReponse();
	// }

	/**
	 * 设置子成员
	 * 
	 * @param id
	 * @return
	 */
	// @PutMapping("/groupUsers/add")
	// public ApiResponse groupUsersAdd(@RequestBody List<String> members,
	// @RequestParam(name = "id")
	// String id) {
	// // 需要递归展开子组，并且避免相互依赖的时候出现死循环
	// List<SwdGroupUser> groupUsers = new ArrayList<SwdGroupUser>();
	// members.forEach(member -> {
	// SwdGroupUser subGroup = new SwdGroupUser();
	// SwdGroupUserPK swdGroupUserPK = new SwdGroupUserPK();
	// swdGroupUserPK.setGroupId(id);
	// swdGroupUserPK.setUsername(member);
	// subGroup.setId(swdGroupUserPK);
	// groupUsers.add(subGroup);
	// });
	// groupUserRepository.saveAll(groupUsers);
	// return ApiResponseFactory.getNormalReponse();
	// }

	/**
	 * 移除子成员
	 * 
	 * @param id
	 * @return
	 */
	// @PutMapping("/groupUsers/remove")
	// public ApiResponse groupUsersRemove(@RequestBody List<String> members,
	// @RequestParam(name =
	// "id") String id) {
	// // 需要递归展开子组，并且避免相互依赖的时候出现死循环
	// List<SwdGroupUser> groupUsers = new ArrayList<SwdGroupUser>();
	// members.forEach(member -> {
	// SwdGroupUser subGroup = new SwdGroupUser();
	// SwdGroupUserPK swdGroupUserPK = new SwdGroupUserPK();
	// swdGroupUserPK.setGroupId(id);
	// swdGroupUserPK.setUsername(member);
	// subGroup.setId(swdGroupUserPK);
	// groupUsers.add(subGroup);
	// });
	// groupUserRepository.deleteAll(groupUsers);
	// return ApiResponseFactory.getNormalReponse();
	// }
}

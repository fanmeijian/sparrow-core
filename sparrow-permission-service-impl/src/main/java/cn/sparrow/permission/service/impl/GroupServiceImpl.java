package cn.sparrow.permission.service.impl;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.SparrowTree;
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
import cn.sparrow.permission.model.organization.OrganizationGroup;
import cn.sparrow.permission.model.organization.OrganizationGroupPK;
import cn.sparrow.permission.service.GroupService;
import cn.sparrow.permission.service.repository.GroupEmployeeRepository;
import cn.sparrow.permission.service.repository.GroupLevelRepository;
import cn.sparrow.permission.service.repository.GroupOrganizationRepository;
import cn.sparrow.permission.service.repository.GroupRelationRepository;
import cn.sparrow.permission.service.repository.GroupRepository;
import cn.sparrow.permission.service.repository.GroupRoleRepository;
import cn.sparrow.permission.service.repository.GroupSysroleRepository;
import cn.sparrow.permission.service.repository.GroupUserRepository;
import cn.sparrow.permission.service.repository.OrganizationGroupRepository;

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

	public void addRelations(Set<GroupRelation> ids) {
		groupRelationRepository.saveAll(ids);
	}

	public void delRelations(Set<GroupRelationPK> ids) {
		ids.forEach(f -> {
			groupRelationRepository.deleteById(f);
		});
	}

	public void addOrganizations(Set<GroupOrganizationPK> ids) {
		ids.forEach(f -> {
			groupOrganizationRepository.save(new GroupOrganization(f));
		});
	}

	public void delOrganizations(Set<GroupOrganizationPK> ids) {
		ids.forEach(f -> {
			groupOrganizationRepository.delete(new GroupOrganization(f));
		});
	}

	public void addRoles(Set<GroupRolePK> ids) {
		ids.forEach(f -> {
			groupRoleRepository.save(new GroupRole(f));
		});
	}

	public void delRoles(Set<GroupRolePK> ids) {
		ids.forEach(f -> {
			groupRoleRepository.delete(new GroupRole(f));
		});
	}

	public void addLevels(Set<GroupPositionLevelPK> ids) {
		ids.forEach(f -> {
			groupLevelRepository.save(new GroupPositionLevel(f));
		});
	}

	public void delLevels(Set<GroupPositionLevelPK> ids) {
		ids.forEach(f -> {
			groupLevelRepository.delete(new GroupPositionLevel(f));
		});
	}

	public void addSysroles(Set<GroupSysrolePK> ids) {
		ids.forEach(f -> {
			groupSysroleRepository.save(new GroupSysrole(f));
		});
	}

	public void delSysroles(Set<GroupSysrolePK> ids) {
		ids.forEach(f -> {
			groupSysroleRepository.delete(new GroupSysrole(f));
		});
	}

	public void addUsers(Set<GroupUserPK> ids) {
		ids.forEach(f -> {
			groupUserRepository.save(new GroupUser(f));
		});
	}

	public void delUsers(Set<GroupUserPK> ids) {
		ids.forEach(f -> {
			groupUserRepository.delete(new GroupUser(f));
		});
	}

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

	public Group save(Group group) {
		Group savedGroup = groupRepository.save(group);
		// save relation
		savedGroup.getOrganizationIds().forEach(f->{
			organizationGroupRepository.save(new OrganizationGroup(new OrganizationGroupPK(f,savedGroup.getId())));
		});
		return savedGroup;
	}

	@Transactional
	public void saveGroupMember(GroupMember groupMember) {
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

	public GroupMember getGroupMember(@NotBlank String groupId) {
		GroupMember groupMember = new GroupMember();
		groupMember.setGroupEmployees(groupEmployeeRepository.findByIdGroupId(groupId));
		groupMember.setGroupRelations(groupRelationRepository.findByIdParentId(groupId));
		groupMember.setGroupOrganizations(groupOrganizationRepository.findByIdGroupId(groupId));
		groupMember.setGroupRoles(groupRoleRepository.findByIdGroupId(groupId));
		groupMember.setGroupLevels(groupLevelRepository.findByIdGroupId(groupId));
		groupMember.setGroupSysroles(groupSysroleRepository.findByIdGroupId(groupId));

		return groupMember;
	}

	public List<Employee> getFinalEmployees(@NotBlank String groupId) {
		// get the actual employees from all group member except organization for organization member are too large
		
		return null;
	}

	@Override
	public void addMembers(GroupMember groupMember) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(List<Group> groups) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(List<Group> groups) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SparrowTree<Group, String> tree(String parentId) {
		// TODO Auto-generated method stub
		return null;
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

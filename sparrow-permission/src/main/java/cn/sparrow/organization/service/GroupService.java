package cn.sparrow.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import cn.sparrow.organization.repository.GroupRepository;
import cn.sparrow.organization.repository.GroupUserRepository;

/**
 * 群组服务
 * 
 * @author fanmj
 *
 */

@Service
public class GroupService {

	@Autowired
	GroupUserRepository groupUserRepository;

//	@Autowired
//	SubGroupRepository subGroupRepository;

	@Autowired
	GroupRepository groupRepository;

	/**
	 * 以树的形式返回
	 * 
	 * @param id
	 * @return
	 */
//	@GetMapping("/subGroups/tree")
//	public ApiResponse subGroupsRecursionTree(@RequestParam(name = "id") String id) {
//		Map<String, SwdGroup> stopRecursion = new HashMap<String, SwdGroup>();// 防止递归
//		GroupExt groupExt = new GroupExt(groupRepository.findById(id).get());
////		groupExt.getGroupUsers().addAll(groupUserRepository.findByIdGroupId(id, Pageable.unpaged()).toList());
//		stopRecursion.put(groupExt.getId(), groupExt);
//		subGroupsTree(groupExt, stopRecursion);
//		return ApiResponseFactory.getNormalReponse(groupExt);
//	}
//
//	private void subGroupsTree(GroupExt groupExt, Map<String, SwdGroup> map) {
//		for (GroupExt group : subGroupRepository.findByGroupId(groupExt.getId())) {
//			groupExt.getSubGroups().add(group);
//			if (!map.containsKey(group.getId())) {
//				map.put(group.getId(), group);
//				groupExt.getGroupUsers().addAll(groupUserRepository.findByIdGroupId(group.getId(),Pageable.unpaged()).toList());
//				subGroupsTree(group, map);
//			}
//		}
//	}
	

	/**
	 * 设置子组
	 * 
	 * @return
	 */
//	@PutMapping("/subGroups/add")
//	public ApiResponse subGroupsAdd(@RequestBody List<String> groups, @RequestParam(name = "id") String id) {
//		List<SwdSubGroup> subGroups = new ArrayList<SwdSubGroup>();
//		groups.forEach(group -> {
//			SwdSubGroup subGroup = new SwdSubGroup();
//			SwdSubGroupPK swdSubGroupPK = new SwdSubGroupPK();
//			swdSubGroupPK.setGroupId(id);
//			swdSubGroupPK.setSubGroupId(group);
//			subGroup.setId(swdSubGroupPK);
//			subGroups.add(subGroup);
//		});
//		subGroupRepository.saveAll(subGroups);
//		return ApiResponseFactory.getNormalReponse();
//	}

	/**
	 * 移除子组
	 * 
	 * @return
	 */
//	@PutMapping("/subGroups/remove")
//	public ApiResponse subGroupsRemove(@RequestBody List<String> groups, @RequestParam(name = "id") String id) {
//		List<SwdSubGroup> subGroups = new ArrayList<SwdSubGroup>();
//		groups.forEach(group -> {
//			SwdSubGroup subGroup = new SwdSubGroup();
//			SwdSubGroupPK swdSubGroupPK = new SwdSubGroupPK();
//			swdSubGroupPK.setGroupId(id);
//			swdSubGroupPK.setSubGroupId(group);
//			subGroup.setId(swdSubGroupPK);
//			subGroups.add(subGroup);
//		});
//		subGroupRepository.deleteAll(subGroups);
//		return ApiResponseFactory.getNormalReponse();
//	}

	/**
	 * 设置子成员
	 * 
	 * @param id
	 * @return
	 */
//	@PutMapping("/groupUsers/add")
//	public ApiResponse groupUsersAdd(@RequestBody List<String> members, @RequestParam(name = "id") String id) {
//		// 需要递归展开子组，并且避免相互依赖的时候出现死循环
//		List<SwdGroupUser> groupUsers = new ArrayList<SwdGroupUser>();
//		members.forEach(member -> {
//			SwdGroupUser subGroup = new SwdGroupUser();
//			SwdGroupUserPK swdGroupUserPK = new SwdGroupUserPK();
//			swdGroupUserPK.setGroupId(id);
//			swdGroupUserPK.setUsername(member);
//			subGroup.setId(swdGroupUserPK);
//			groupUsers.add(subGroup);
//		});
//		groupUserRepository.saveAll(groupUsers);
//		return ApiResponseFactory.getNormalReponse();
//	}

	/**
	 * 移除子成员
	 * 
	 * @param id
	 * @return
	 */
//	@PutMapping("/groupUsers/remove")
//	public ApiResponse groupUsersRemove(@RequestBody List<String> members, @RequestParam(name = "id") String id) {
//		// 需要递归展开子组，并且避免相互依赖的时候出现死循环
//		List<SwdGroupUser> groupUsers = new ArrayList<SwdGroupUser>();
//		members.forEach(member -> {
//			SwdGroupUser subGroup = new SwdGroupUser();
//			SwdGroupUserPK swdGroupUserPK = new SwdGroupUserPK();
//			swdGroupUserPK.setGroupId(id);
//			swdGroupUserPK.setUsername(member);
//			subGroup.setId(swdGroupUserPK);
//			groupUsers.add(subGroup);
//		});
//		groupUserRepository.deleteAll(groupUsers);
//		return ApiResponseFactory.getNormalReponse();
//	}
}

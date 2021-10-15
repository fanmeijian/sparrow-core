package cn.sparrow.organization.service;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.Group;
import cn.sparrow.model.organization.GroupLevel;
import cn.sparrow.model.organization.GroupLevelPK;
import cn.sparrow.model.organization.GroupOrganization;
import cn.sparrow.model.organization.GroupOrganizationPK;
import cn.sparrow.model.organization.GroupRelation;
import cn.sparrow.model.organization.GroupRelationPK;
import cn.sparrow.model.organization.GroupRole;
import cn.sparrow.model.organization.GroupRolePK;
import cn.sparrow.model.organization.GroupSysrole;
import cn.sparrow.model.organization.GroupSysrolePK;
import cn.sparrow.model.organization.GroupUser;
import cn.sparrow.model.organization.GroupUserPK;
import cn.sparrow.organization.repository.GroupLevelRepository;
import cn.sparrow.organization.repository.GroupOrganizationRepository;
import cn.sparrow.organization.repository.GroupRelationRepository;
import cn.sparrow.organization.repository.GroupRepository;
import cn.sparrow.organization.repository.GroupRoleRepository;
import cn.sparrow.organization.repository.GroupSysroleRepository;
import cn.sparrow.organization.repository.GroupUserRepository;

/**
 * 群组服务
 * 
 * @author fanmj
 *
 */

@Service
public class GroupService {

  @Autowired GroupUserRepository groupUserRepository;
  @Autowired GroupOrganizationRepository groupOrganizationRepository;
  @Autowired GroupRoleRepository groupRoleRepository;
  @Autowired GroupLevelRepository groupLevelRepository;
  @Autowired GroupRelationRepository groupRelationRepository;
  @Autowired GroupSysroleRepository groupSysroleRepository;

  // @Autowired
  // SubGroupRepository subGroupRepository;

  @Autowired
  GroupRepository groupRepository;

  public void addRelations(Set<GroupRelationPK> ids) {
    ids.forEach(f -> {
      groupRelationRepository.save(new GroupRelation(f));
    });
  }

  public void delRelations(Set<GroupRelationPK> ids) {
    ids.forEach(f -> {
      groupRelationRepository.deleteById(f);
    });
  }
  
  public void addOrganizations(Set<GroupOrganizationPK> ids) {
    ids.forEach(f->{
      groupOrganizationRepository.save(new GroupOrganization(f));
    });
  }

  public void delOrganizations(Set<GroupOrganizationPK> ids) {
    ids.forEach(f->{
      groupOrganizationRepository.delete(new GroupOrganization(f));
    });
  }

  public void addRoles(Set<GroupRolePK> ids) {
    ids.forEach(f->{
      groupRoleRepository.save(new GroupRole(f));
    });
  }

  public void delRoles(Set<GroupRolePK> ids) {
    ids.forEach(f->{
      groupRoleRepository.delete(new GroupRole(f));
    });
  }
  
  public void addLevels(Set<GroupLevelPK> ids) {
    ids.forEach(f->{
      groupLevelRepository.save(new GroupLevel(f));
    });
  }

  public void delLevels(Set<GroupLevelPK> ids) {
    ids.forEach(f->{
      groupLevelRepository.delete(new GroupLevel(f));
    });
  }
  
  public void addSysroles(Set<GroupSysrolePK> ids) {
    ids.forEach(f->{
      groupSysroleRepository.save(new GroupSysrole(f));
    });
  }

  public void delSysroles(Set<GroupSysrolePK> ids) {
    ids.forEach(f->{
      groupSysroleRepository.delete(new GroupSysrole(f));
    });
  }
  
  public void addUsers(Set<GroupUserPK> ids) {
    ids.forEach(f->{
      groupUserRepository.save(new GroupUser(f));
    });
  }

  public void delUsers(Set<GroupUserPK> ids) {
    ids.forEach(f->{
      groupUserRepository.delete(new GroupUser(f));
    });
  }
  
  
  public MyTree<Group> getTree(String parentId) {
    MyTree<Group> myTree = new MyTree<Group>(parentId==null?null:groupRepository.findById(parentId).orElse(null));
    buildTree(myTree);
    return myTree;
  }

  public void buildTree(MyTree<Group> myTree) {
    groupRelationRepository.findByIdParentId(myTree.getMe()==null?null:myTree.getMe().getId()).forEach(f -> {
      MyTree<Group> leaf = new MyTree<Group>(f.getGroup());
      // 防止死循环
      if(groupRelationRepository.findById(new GroupRelationPK(f.getId().getParentId(), f.getId().getGroupId())).orElse(null)==null)
        buildTree(leaf);
      myTree.getChildren().add(leaf);
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
  // public ApiResponse subGroupsRecursionTree(@RequestParam(name = "id") String id) {
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
  // public ApiResponse subGroupsAdd(@RequestBody List<String> groups, @RequestParam(name = "id")
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
  // public ApiResponse subGroupsRemove(@RequestBody List<String> groups, @RequestParam(name = "id")
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
  // public ApiResponse groupUsersAdd(@RequestBody List<String> members, @RequestParam(name = "id")
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
  // public ApiResponse groupUsersRemove(@RequestBody List<String> members, @RequestParam(name =
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

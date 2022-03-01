package cn.sparrow.permission.model.group;

import java.util.List;

import lombok.Data;

@Data
public class GroupMember {
	private List<GroupRelation> groupRelations;
	private List<GroupOrganization> groupOrganizations;
	private List<GroupRole> groupRoles;
	private List<GroupPositionLevel> groupLevels;
	private List<GroupSysrole> groupSysroles;
	private List<GroupEmployee> groupEmployees;
}

package cn.sparrow.permission.model.group;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class GroupMember implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<GroupRelation> groupRelations;
	private List<GroupOrganization> groupOrganizations;
	private List<GroupRole> groupRoles;
	private List<GroupPositionLevel> groupLevels;
	private List<GroupSysrole> groupSysroles;
	private List<GroupEmployee> groupEmployees;
}

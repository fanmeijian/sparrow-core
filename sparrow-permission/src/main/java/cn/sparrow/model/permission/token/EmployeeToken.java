package cn.sparrow.model.permission.token;

import java.util.List;

import cn.sparrow.model.organization.Organization;
import cn.sparrow.model.organization.OrganizationGroup;
import cn.sparrow.model.organization.OrganizationPositionLevel;
import cn.sparrow.model.organization.OrganizationRole;
import cn.sparrow.model.permission.Sysrole;

public class EmployeeToken {
	List<String> usernames; // the employee will have more than one login account
	List<Sysrole> sysroles;
	List<OrganizationGroup> groups;
	List<Organization> organizations;
	List<OrganizationRole> roles;
	List<OrganizationPositionLevel> positionLevels;
}

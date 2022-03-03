package cn.sparrow.permission.service;

import java.io.Serializable;
import java.util.List;

import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String employeeId;
	List<String> usernames; // the employee will have more than one login account
	private List<String> sysroleIds;
	private List<String> groupIds;
	private List<String> organizationIds;
	private List<OrganizationRolePK> roleIds;
	private List<OrganizationPositionLevelPK> positionLevelIds;

}

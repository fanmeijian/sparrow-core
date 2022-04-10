package cn.sparrow.permission.model.token;

import java.io.Serializable;
import java.util.List;

import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class EmployeeToken extends UserToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Include
	private String employeeId;
	private List<String> groupIds;
	private List<String> organizationIds;
	private List<OrganizationRolePK> roleIds;
	private List<OrganizationPositionLevelPK> positionLevelIds;
	
	private List<String> roleGroups;
	private List<String> jobLevelGroups;

}

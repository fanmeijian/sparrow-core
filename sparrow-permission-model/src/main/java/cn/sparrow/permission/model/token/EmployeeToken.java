package cn.sparrow.permission.model.token;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
	private List<String> empGroups;
	private List<String> organizationIds;
	private List<OrganizationRolePK> orgRoleIds;
	private List<OrganizationPositionLevelPK> orgJobLevelIds;

	private Set<String> roleGroups;
	private Set<String> jobLevelGroups;
	private Set<String> orgGroups;

	public Set<String> getAllGroups() {
		Set<String> allGroupSet = super.getAllGroups();

		if (this.roleGroups != null && this.roleGroups.size() > 0) {
			allGroupSet.addAll(this.roleGroups);
		}

		if (this.jobLevelGroups != null && this.jobLevelGroups.size() > 0) {
			allGroupSet.addAll(this.jobLevelGroups);
		}

		if (this.orgGroups != null && this.orgGroups.size() > 0) {
			allGroupSet.addAll(this.orgGroups);
		}

		return allGroupSet;
	}

}

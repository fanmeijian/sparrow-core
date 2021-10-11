package cn.sparrow.model.organization;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 4:56:54 PM
 */

@Embeddable
@Setter
@Getter
public class EmployeeOrganizationLevelPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrganizationLevelPK organizationLevelId;
	private String username;

	public EmployeeOrganizationLevelPK(){

	}

	@Override
	public int hashCode() {
		return Objects.hash(organizationLevelId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeOrganizationLevelPK other = (EmployeeOrganizationLevelPK) obj;
		return Objects.equals(organizationLevelId, other.organizationLevelId)
				&& Objects.equals(username, other.username);
	}

	public EmployeeOrganizationLevelPK(OrganizationLevelPK organizationLevelId, String username) {
		super();
		this.organizationLevelId = organizationLevelId;
		this.username = username;
	}
	
	
}
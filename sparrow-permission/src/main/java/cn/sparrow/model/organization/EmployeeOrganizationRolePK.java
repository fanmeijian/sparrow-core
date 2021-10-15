package cn.sparrow.model.organization;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;


@Embeddable
@Setter
@Getter
public class EmployeeOrganizationRolePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrganizationRolePK organizationRoleId;
	private String username;

	public EmployeeOrganizationRolePK(){

	}

	public EmployeeOrganizationRolePK(OrganizationRolePK organizationRoleId, String username) {
		super();
		this.organizationRoleId = organizationRoleId;
		this.username = username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(organizationRoleId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeOrganizationRolePK other = (EmployeeOrganizationRolePK) obj;
		return Objects.equals(organizationRoleId, other.organizationRoleId) && Objects.equals(username, other.username);
	}

}
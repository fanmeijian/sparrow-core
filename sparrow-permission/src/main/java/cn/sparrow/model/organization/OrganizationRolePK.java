package cn.sparrow.model.organization;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;


@Embeddable
@Setter
@Getter
public class OrganizationRolePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "organization_id")
	private String organizationId;
	@Column(name = "role_id")
	private String roleId;

	public OrganizationRolePK(){

	}

	public OrganizationRolePK(String organizationId, String roleId) {
		super();
		this.organizationId = organizationId;
		this.roleId = roleId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(organizationId, roleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganizationRolePK other = (OrganizationRolePK) obj;
		return Objects.equals(organizationId, other.organizationId) && Objects.equals(roleId, other.roleId);
	}

}
package cn.sparrow.model.organization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@NoArgsConstructor
public class OrganizationRolePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "organization_id")
	private String organizationId;
	@Column(name = "role_id")
	private String roleId;
	
	public OrganizationRolePK(String organizationId, String roleId) {
		this.organizationId = organizationId;
		this.roleId = roleId;
	}

}
package cn.sparrow.model.organization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrganizationRolePK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "organization_id")
	private String organizationId;
	
	@Column(name = "role_id")
	private String roleId;


}
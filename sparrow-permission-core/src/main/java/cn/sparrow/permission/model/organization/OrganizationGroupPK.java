package cn.sparrow.permission.model.organization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrganizationGroupPK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Column(name = "organization_id")
	private String organizationId;
	@Column(name = "group_id")
	private String groupId;
}

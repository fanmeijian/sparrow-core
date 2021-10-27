package cn.sparrow.model.organization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@NoArgsConstructor
public class OrganizationLevelPK implements Serializable{

	private static final long serialVersionUID = 1L;
	@Column(name = "organization_id")
	private String organizationId;
	@Column(name = "level_id")
	private String positionLevelId;
	
	
	public OrganizationLevelPK(String organizationId, String levelId) {
		this.organizationId = organizationId;
		this.positionLevelId = levelId;
	}
}
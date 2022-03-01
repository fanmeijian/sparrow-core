package cn.sparrow.model.organization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@NoArgsConstructor
public class OrganizationPositionLevelPK implements Serializable{

	private static final long serialVersionUID = 1L;
	@Column(name = "organization_id")
	private String organizationId;
	@Column(name = "position_level_id")
	private String positionLevelId;
	
	
	public OrganizationPositionLevelPK(String organizationId, String levelId) {
		this.organizationId = organizationId;
		this.positionLevelId = levelId;
	}
}
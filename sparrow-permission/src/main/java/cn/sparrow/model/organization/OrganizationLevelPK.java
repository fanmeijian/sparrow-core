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
public class OrganizationLevelPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "level_id")
	private String levelId;
	@Column(name = "organization_id")
	private String organizationId;

	public OrganizationLevelPK(){

	}

	public OrganizationLevelPK(String levelId, String organizationId) {
		super();
		this.levelId = levelId;
		this.organizationId = organizationId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(levelId, organizationId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganizationLevelPK other = (OrganizationLevelPK) obj;
		return Objects.equals(levelId, other.levelId) && Objects.equals(organizationId, other.organizationId);
	}
	
}
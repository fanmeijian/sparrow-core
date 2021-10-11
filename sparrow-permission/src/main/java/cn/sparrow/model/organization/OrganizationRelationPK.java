package cn.sparrow.model.organization;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 4:57:01 PM
 */

@Embeddable
@Setter
@Getter
public class OrganizationRelationPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "organization_id")
	private String organizationId;
	@Column(name = "organization_relation_type")
	private String organizationRelationType;
	@Column(name = "target_organization_id")
	private String targetOrganizationId;

	public OrganizationRelationPK(){

	}

	public OrganizationRelationPK(String organizationId, String organizationRelationId, String targetOrganizationId) {
		super();
		this.organizationId = organizationId;
		this.organizationRelationType = organizationRelationId;
		this.targetOrganizationId = targetOrganizationId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(organizationId, organizationRelationType, targetOrganizationId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganizationRelationPK other = (OrganizationRelationPK) obj;
		return Objects.equals(organizationId, other.organizationId)
				&& Objects.equals(organizationRelationType, other.organizationRelationType)
				&& Objects.equals(targetOrganizationId, other.targetOrganizationId);
	}

}
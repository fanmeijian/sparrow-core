package cn.sparrow.model.organization;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * ��������֯
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 4:57:06 PM
 */

@Embeddable
@Setter
@Getter
public class GroupOrganizationPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "group_id")
	private String groupId;
	@Column(name = "organization_id")
	private String organizationId;

	public GroupOrganizationPK(){

	}

	public GroupOrganizationPK(String groupId, String organizationId) {
		super();
		this.groupId = groupId;
		this.organizationId = organizationId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(groupId, organizationId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupOrganizationPK other = (GroupOrganizationPK) obj;
		return Objects.equals(groupId, other.groupId) && Objects.equals(organizationId, other.organizationId);
	}

}
package cn.sparrow.permission.model.group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.model.organization.Organization;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_group_organization")
@NamedQueries({@NamedQuery(name = "GroupOrganization.findByOrgId", query = "SELECT go FROM GroupOrganization go WHERE go.id.organizationId = :orgId")})
public class GroupOrganization extends AbstractSparrowEntity {

	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@EmbeddedId
	@Audited
	private GroupOrganizationPK id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "group_id", insertable = false, updatable = false)
	private Group group;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private Organization organization;

	public GroupOrganization(GroupOrganizationPK f) {
		this.id = f;
	}

	public GroupOrganization(String groupId, String organizationId) {
		this.id = new GroupOrganizationPK(groupId, organizationId);
	}

}

package cn.sparrow.permission.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.model.group.Group;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spr_organization_group")
public class OrganizationGroup extends AbstractSparrowEntity {
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@EmbeddedId
	@Audited
	private OrganizationGroupPK id;
	@Audited
	private String stat;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private Organization organization;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "group_id", insertable = false, updatable = false)
	private Group group;

	public OrganizationGroup(OrganizationGroupPK f) {
		this.id = f;
	}

    public OrganizationGroup(String organizationId, String groupId) {
		this.id = new OrganizationGroupPK(organizationId, groupId);
    }
}

package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.sparrow.model.common.AbstractOperationLog;
import cn.sparrow.model.group.Group;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode.Exclude;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "spr_organization_group")
@EntityListeners(AuditingEntityListener.class)
public class OrganizationGroup extends AbstractOperationLog implements Persistable<OrganizationGroupPK> {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private OrganizationGroupPK id;
	private String stat;

	@Exclude
	@ManyToOne
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private Organization organization;
	
	@Exclude
	@ManyToOne
	@JoinColumn(name = "group_id", insertable = false, updatable = false)
	private Group group;

	public OrganizationGroup(OrganizationGroupPK f) {
		this.id = f;
	}

	@Override
	public boolean isNew() {
		return true;
	}
}

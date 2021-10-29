package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;

import cn.sparrow.model.common.AbstractOperationLog;
import cn.sparrow.model.group.Group;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spr_organization_group")
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
	

	public OrganizationGroup() {

	}

	public OrganizationGroup(OrganizationGroupPK f) {
		this.id = f;
	}

	@Override
	public boolean isNew() {
		return true;
	}
}

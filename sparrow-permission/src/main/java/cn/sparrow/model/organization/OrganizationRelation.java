package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.domain.Persistable;
import cn.sparrow.model.permission.AbstractOperationLog;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 4:57:04 PM
 */

@Getter
@Setter
@Entity
@Table(name = "spr_organization_relation")
public class OrganizationRelation extends AbstractOperationLog implements Persistable<OrganizationRelationPK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private OrganizationRelationPK id;
	private String stat;
	
	@ManyToOne
	@JoinColumn(name = "organization_relation_type", insertable = false, updatable = false)
	private OrganizationRelationType organizationRelationType;

	public OrganizationRelation() {

	}

	@Override
	public boolean isNew() {
		return true;
	}

	public OrganizationRelation(OrganizationRelationPK id, String stat) {
		super();
		this.id = id;
		this.stat = stat;
	}

}
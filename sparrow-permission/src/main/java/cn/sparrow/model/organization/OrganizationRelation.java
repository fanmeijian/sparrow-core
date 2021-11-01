package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "spr_organization_relation")
@EntityListeners(AuditingEntityListener.class)
public class OrganizationRelation extends AbstractOperationLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@EmbeddedId
	private OrganizationRelationPK id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private Organization organization;

	@ManyToOne
	@JoinColumn(name = "parent_id", insertable = false, updatable = false)
	private Organization parent;

	public OrganizationRelation(OrganizationRelationPK id) {
		this.id = id;
	}

	public OrganizationRelation(Organization f) {
		this.organization = f;
	}

}

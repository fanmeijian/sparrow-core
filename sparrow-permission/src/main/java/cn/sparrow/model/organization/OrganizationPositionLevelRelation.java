package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "spr_organization_position_level_relation")
@EntityListeners(AuditingEntityListener.class)
public class OrganizationPositionLevelRelation extends AbstractOperationLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrganizationPositionLevelRelationPK id;

	@Exclude
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "organization_id", referencedColumnName = "organization_id", insertable = false, updatable = false),
			@JoinColumn(name = "position_level_id", referencedColumnName = "position_level_id", insertable = false, updatable = false) })
	private OrganizationPositionLevel organizationLevel;

	@Exclude
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "parent_organization_id", referencedColumnName = "organization_id", insertable = false, updatable = false),
			@JoinColumn(name = "parent_position_level_id", referencedColumnName = "position_level_id", insertable = false, updatable = false) })
	private OrganizationPositionLevel parentOrganizationLevel;

	public OrganizationPositionLevelRelation(OrganizationPositionLevelRelationPK id) {
		this.id = id;
	}
}

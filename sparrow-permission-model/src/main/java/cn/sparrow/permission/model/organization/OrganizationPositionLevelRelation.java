package cn.sparrow.permission.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.ValidationException;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_organization_position_level_relation")
public class OrganizationPositionLevelRelation extends AbstractSparrowEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@EmbeddedId
	private OrganizationPositionLevelRelationPK id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "organization_id", referencedColumnName = "organization_id", insertable = false, updatable = false),
			@JoinColumn(name = "position_level_id", referencedColumnName = "position_level_id", insertable = false, updatable = false) })
	private OrganizationPositionLevel organizationLevel;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "parent_organization_id", referencedColumnName = "organization_id", insertable = false, updatable = false),
			@JoinColumn(name = "parent_position_level_id", referencedColumnName = "position_level_id", insertable = false, updatable = false) })
	private OrganizationPositionLevel parentOrganizationLevel;

	public OrganizationPositionLevelRelation(OrganizationPositionLevelRelationPK id) {
		this.id = id;
	}

	@PrePersist
	@PreUpdate
	private void preSave() {
		if (id.getId().equals(id.getParentId())) {
			throw new ValidationException("can not add relation to self");
		}
	}
}

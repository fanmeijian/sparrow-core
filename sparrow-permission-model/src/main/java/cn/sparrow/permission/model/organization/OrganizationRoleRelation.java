package cn.sparrow.permission.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.ValidationException;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_organization_role_relation")
public class OrganizationRoleRelation extends AbstractSparrowEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@EmbeddedId
//	@Audited
	private OrganizationRoleRelationPK id;

	@JsonIgnore
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "organization_id", referencedColumnName = "organization_id", insertable = false, updatable = false),
			@JoinColumn(name = "role_id", referencedColumnName = "role_id", insertable = false, updatable = false) })
	private OrganizationRole organizationRole;

	@JsonIgnore
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "parent_organization_id", referencedColumnName = "organization_id", insertable = false, updatable = false),
			@JoinColumn(name = "parent_role_id", referencedColumnName = "role_id", insertable = false, updatable = false) })
	private OrganizationRole parentOrganizationRole;

	public OrganizationRoleRelation(OrganizationRoleRelationPK id) {
		this.id = id;
	}

	public OrganizationRoleRelation(OrganizationRolePK id, OrganizationRolePK parentId) {
		this.id = new OrganizationRoleRelationPK(id, parentId);
	}

	@PrePersist
	@PreUpdate
	private void preSave() {
		if (id.getId().equals(id.getParentId())) {
			throw new ValidationException("can not add relation to self");
		}
	}

}

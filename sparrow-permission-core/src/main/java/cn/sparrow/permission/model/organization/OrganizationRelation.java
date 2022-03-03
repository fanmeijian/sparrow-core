package cn.sparrow.permission.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.ValidationException;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.model.AbstractOperationLog;
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
@NamedQueries({
		@NamedQuery(name = "OrganizationRelation.findByOrganizationId", query = "SELECT o FROM OrganizationRelation o WHERE o.id.organizationId = :organizationId"),
		@NamedQuery(name = "OrganizationRelation.findByParentId", query = "SELECT o FROM OrganizationRelation o WHERE o.id.parentId = :parentId") })
public class OrganizationRelation extends AbstractOperationLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@EmbeddedId
	private OrganizationRelationPK id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private Organization organization;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "parent_id", insertable = false, updatable = false)
	private Organization parent;

	public OrganizationRelation(OrganizationRelationPK id) {
		this.id = id;
	}

	public OrganizationRelation(String organizationId, String parentId) {
		this.id = new OrganizationRelationPK(organizationId, parentId);
	}

	public OrganizationRelation(Organization f) {
		this.organization = f;
	}

	@PrePersist
	@PreUpdate
	private void preSave() {
		if (id.getOrganizationId().equals(id.getParentId())) {
			throw new ValidationException("can not add relation to self");
		}
	}

}

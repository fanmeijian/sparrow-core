package cn.sparrow.model.organization;

import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "spr_organization_role")
@EntityListeners(AuditingEntityListener.class)
public class OrganizationRole extends AbstractOperationLog implements Persistable<OrganizationRolePK> {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private OrganizationRolePK id;
	private String stat;

	@Transient
	@JsonProperty
	private boolean hasChildren;
	
	@Transient
	@JsonProperty
	private long childCount;

	@Exclude
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "organization_id", referencedColumnName = "organization_id"),
			@JoinColumn(name = "role_id", referencedColumnName = "role_id") })
	private Set<EmployeeOrganizationRole> employeeOrganizationRoles;
	
	@Transient
	@JsonProperty
	@Exclude
	private Set<OrganizationRelation> reportRoles;
	
	@Transient
	@JsonProperty
	@Exclude
	private Set<OrganizationRelation> reportByRoles;

	@Exclude
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;
	
	@JsonIgnore
	@Exclude
	@ManyToOne
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private Organization organization;

	public OrganizationRole(OrganizationRolePK f) {
		this.id = f;
	}

	@Override
	public boolean isNew() {
		return true;
	}

}

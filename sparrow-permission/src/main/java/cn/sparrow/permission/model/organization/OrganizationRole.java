package cn.sparrow.permission.model.organization;

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

import cn.sparrow.permission.model.AbstractOperationLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_organization_role")
@EntityListeners(AuditingEntityListener.class)
public class OrganizationRole extends AbstractOperationLog implements Persistable<OrganizationRolePK> {

	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@EmbeddedId
	private OrganizationRolePK id;
	private String stat;
	
	@Transient
	@JsonProperty
	private long childCount;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "organization_id", referencedColumnName = "organization_id"),
			@JoinColumn(name = "role_id", referencedColumnName = "role_id") })
	private Set<EmployeeOrganizationRole> employeeOrganizationRoles;
	
	@Transient
	@JsonProperty
	private Set<OrganizationRelation> reportRoles;
	
	@Transient
	@JsonProperty
	private Set<OrganizationRelation> reportByRoles;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;
	
	@JsonIgnore
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

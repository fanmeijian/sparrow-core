package cn.sparrow.permission.model.organization;

import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "spr_organization_role")
public class OrganizationRole extends AbstractSparrowEntity{

	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@EmbeddedId
	@Audited
	private OrganizationRolePK id;
	@Audited
	private String stat;
	
//	@Transient
//	@JsonProperty
//	private long childCount;

	@JsonIgnore
	@OneToMany
	@JoinColumns({ @JoinColumn(name = "organization_id", referencedColumnName = "organization_id"),
			@JoinColumn(name = "role_id", referencedColumnName = "role_id") })
	private Set<EmployeeOrganizationRole> employeeOrganizationRoles;
	
//	@Transient
//	@JsonProperty
//	private Set<OrganizationRelation> reportRoles;
//	
//	@Transient
//	@JsonProperty
//	private Set<OrganizationRelation> reportByRoles;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private Organization organization;

	public OrganizationRole(OrganizationRolePK f) {
		this.id = f;
	}

	public OrganizationRole(String organizationId, String roleId){
		this.id = new OrganizationRolePK(organizationId, roleId);
	}

}

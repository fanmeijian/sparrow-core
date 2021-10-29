package cn.sparrow.model.organization;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.group.GroupEmployee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "spr_employee")
public class Employee extends AbstractSparrowEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String code;
	private boolean root;
	private String username;

	// use for create relation at batch
	@Transient
	@JsonProperty
	private List<String> parentIds;
	
	@Column(name = "organization_id")
	private String organizationId;
	
	@Transient
	@JsonProperty
	private long childCount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private Organization organization;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee",fetch = FetchType.EAGER)
	private Set<EmployeeOrganizationRole> employeeOrganizationRoles;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee",fetch = FetchType.EAGER)
	private Set<EmployeeOrganizationLevel> employeeOrganizationLevels;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.EAGER)
	private Set<GroupEmployee> groupEmployees;

}
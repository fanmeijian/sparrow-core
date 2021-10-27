package cn.sparrow.model.organization;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.model.common.AbstractSparrowEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

	// use for create relation at batch
	@Transient
	@JsonProperty
	private List<String> parentIds;
	
	@Column(name = "organization_id")
	private String organizationId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private Organization organization;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "spr_employee_organization_role", joinColumns = {
			@JoinColumn(name = "employee_id") }, inverseJoinColumns = { @JoinColumn(name = "organization_id"),
					@JoinColumn(name = "role_id") })
	private Set<OrganizationRole> organizationRoles;

	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "spr_employee_organization_level", joinColumns = {
			@JoinColumn(name = "username") }, inverseJoinColumns = { @JoinColumn(name = "organization_id"),
					@JoinColumn(name = "level_id") })
	private Set<OrganizationLevel> organizationLevels;

	private String username;

	public Employee() {

	}

}
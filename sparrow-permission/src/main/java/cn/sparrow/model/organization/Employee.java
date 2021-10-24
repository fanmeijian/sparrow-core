package cn.sparrow.model.organization;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
	
	@ManyToOne
	@JoinColumn(name = "organization_id")
	private Organization organization;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "spr_employee_organization_role", joinColumns = {
			@JoinColumn(name = "username") }, inverseJoinColumns = { @JoinColumn(name = "organization_id"),
					@JoinColumn(name = "role_id") })
	private Set<OrganizationRole> organizationRoles;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "spr_employee_organization_level", joinColumns = {
			@JoinColumn(name = "username") }, inverseJoinColumns = { @JoinColumn(name = "organization_id"),
					@JoinColumn(name = "level_id") })
	private Set<OrganizationLevel> organizationLevels;

	private String username;

	public Employee() {

	}

}
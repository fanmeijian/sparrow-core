package cn.sparrow.model.organization;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import cn.sparrow.model.permission.AbstractSparrowEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 4:56:02 PM
 */
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
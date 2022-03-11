package cn.sparrow.permission.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false,onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spr_employee_organization_position_level")
@NamedQueries({
	@NamedQuery(name = "EmployeeOrganizationLevel.findByEmployeeId", query = "SELECT o FROM EmployeeOrganizationLevel o WHERE o.id.employeeId= :employeeId") })
public class EmployeeOrganizationLevel extends AbstractSparrowEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@EmbeddedId
	private EmployeeOrganizationLevelPK id;
	private String stat;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "organization_id", referencedColumnName = "organization_id", insertable = false, updatable = false),
			@JoinColumn(name = "position_level_id", referencedColumnName = "position_level_id", insertable = false, updatable = false) })
	private OrganizationPositionLevel organizationLevel;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "employee_id", insertable = false, updatable = false)
	private Employee employee;

	public EmployeeOrganizationLevel() {

	}

	public EmployeeOrganizationLevel(EmployeeOrganizationLevelPK f) {
		this.id = f;
	}

	public EmployeeOrganizationLevel(String employeeId, OrganizationPositionLevelPK f) {
		this.id = new EmployeeOrganizationLevelPK(f, employeeId);
	}

}
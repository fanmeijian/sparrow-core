package cn.sparrow.permission.model.organization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeOrganizationRolePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrganizationRolePK organizationRoleId;
	@Column(name = "employee_id")
	private String employeeId;


}
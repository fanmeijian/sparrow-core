package cn.sparrow.model.organization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EmployeeOrganizationLevelPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrganizationLevelPK organizationLevelId;
	@Column(name = "employee_id")
	private String employeeId;
	
}
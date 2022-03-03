package cn.sparrow.permission.model.group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import cn.sparrow.permission.model.AbstractOperationLog;
import cn.sparrow.permission.model.organization.Employee;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_group_employee")
@NamedQueries({
	@NamedQuery(name = "GroupEmployee.findByEmployeeId", query = "SELECT o FROM GroupEmployee o WHERE o.id.employeeId = :employeeId") })
public class GroupEmployee extends AbstractOperationLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@EmbeddedId
	private GroupEmployeePK id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "group_id", insertable = false, updatable = false)
	private Group group;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", insertable = false, updatable = false)
	private Employee employee;

}

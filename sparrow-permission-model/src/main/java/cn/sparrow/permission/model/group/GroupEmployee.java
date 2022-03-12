package cn.sparrow.permission.model.group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
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
public class GroupEmployee extends AbstractSparrowEntity {

	public GroupEmployee(GroupEmployeePK groupEmployeePK) {
		this.id = groupEmployeePK;
    }
	
	public GroupEmployee(String groupId, String employeeId) {
		this.id = new GroupEmployeePK(groupId, employeeId);
    }

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Audited
	@EqualsAndHashCode.Include
	@EmbeddedId
	private GroupEmployeePK id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "group_id", insertable = false, updatable = false)
	private Group group;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "employee_id", insertable = false, updatable = false)
	private Employee employee;

}

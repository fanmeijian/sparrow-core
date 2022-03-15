package cn.sparrow.permission.model.organization;

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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false,onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spr_employee_user")
@NoArgsConstructor
@NamedQueries({
		@NamedQuery(name = "EmployeeUser.findByUsername", query = "SELECT e FROM EmployeeUser e WHERE e.id.username=: username"),
		@NamedQuery(name = "EmployeeUser.findByEmployeeId", query = "SELECT e FROM EmployeeUser e WHERE e.id.employeeId=: employeeId"),
		@NamedQuery(name = "EmployeeUser.findAll", query = "SELECT e FROM EmployeeUser e")})
public class EmployeeUser extends AbstractSparrowEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeUser(EmployeeUserPK employeeUserPK) {
		this.id = employeeUserPK;
	}

	@EqualsAndHashCode.Include
	@EmbeddedId
	@Audited
	private EmployeeUserPK id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "employee_id", insertable = false, updatable = false)
	private Employee employee;
}

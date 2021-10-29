package cn.sparrow.model.group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.sparrow.model.common.AbstractOperationLog;
import cn.sparrow.model.organization.Employee;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "spr_group_employee")
@EntityListeners(AuditingEntityListener.class)
public class GroupEmployee extends AbstractOperationLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GroupEmployeePK id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "group_id", insertable = false, updatable = false)
	private Group group;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", insertable = false, updatable = false)
	private Employee employee;

}

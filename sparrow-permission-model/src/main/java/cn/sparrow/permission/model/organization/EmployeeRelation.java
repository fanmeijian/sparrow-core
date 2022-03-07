package cn.sparrow.permission.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.ValidationException;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_employee_relation")
public class EmployeeRelation extends AbstractSparrowEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@EmbeddedId
	private EmployeeRelationPK id;

	@ManyToOne
	@JoinColumn(name = "employee_id", insertable = false, updatable = false)
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "parent_id	", insertable = false, updatable = false)
	private Employee parent;

	public EmployeeRelation(EmployeeRelationPK f) {
		this.id = f;
	}

	@PrePersist
	@PreUpdate
	private void preSave() {
		if (id.getEmployeeId().equals(id.getParentId())) {
			throw new ValidationException("can not add relation to self");
		}
	}
}

package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.data.domain.Persistable;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "spr_employee_organization_role")
public class EmployeeOrganizationRole extends AbstractOperationLog implements Persistable<EmployeeOrganizationRolePK>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private EmployeeOrganizationRolePK id;
	private String stat;

	public EmployeeOrganizationRole(){

	}
	
	public EmployeeOrganizationRole(EmployeeOrganizationRolePK f) {
    this.id = f;
  }

  @Override
	public boolean isNew() {
		return true;
	}

}
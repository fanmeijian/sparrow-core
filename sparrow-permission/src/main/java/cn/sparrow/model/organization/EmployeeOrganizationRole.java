package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.data.domain.Persistable;
import cn.sparrow.model.permission.AbstractOperationLog;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 4:56:51 PM
 */

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
	
	@Override
	public boolean isNew() {
		return true;
	}

}
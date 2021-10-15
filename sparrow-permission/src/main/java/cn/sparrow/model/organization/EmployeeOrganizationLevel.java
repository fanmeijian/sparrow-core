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
@Table(name = "spr_employee_organization_level")
public class EmployeeOrganizationLevel extends AbstractOperationLog implements Persistable<EmployeeOrganizationLevelPK>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private EmployeeOrganizationLevelPK id;
	private String stat;

	public EmployeeOrganizationLevel(){

	}

	public EmployeeOrganizationLevel(EmployeeOrganizationLevelPK f) {
    this.id = f;
  }

  @Override
	public boolean isNew() {
		return true;
	}


}
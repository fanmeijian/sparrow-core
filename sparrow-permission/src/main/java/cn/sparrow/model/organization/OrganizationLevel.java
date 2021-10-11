package cn.sparrow.model.organization;

import java.util.Set;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.data.domain.Persistable;
import cn.sparrow.model.permission.AbstractOperationLog;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 4:56:45 PM
 */

@Getter
@Setter
@Entity
@Table(name = "spr_organization_level")
public class OrganizationLevel extends AbstractOperationLog implements Persistable<OrganizationLevelPK>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private OrganizationLevelPK id;
	private String stat;
	
	@ManyToMany(mappedBy = "organizationLevels")
	private Set<Employee> employees;

	public OrganizationLevel(){

	}

	@Override
	public boolean isNew() {
		return true;
	}


}
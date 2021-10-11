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
 * @created 28-Sep-2021 4:56:35 PM
 */

@Getter
@Setter
@Entity
@Table(name = "spr_organization_role")
public class OrganizationRole extends AbstractOperationLog implements Persistable<OrganizationRolePK> {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private OrganizationRolePK id;
	private String stat;

	@ManyToMany(mappedBy = "organizationRoles")
	private Set<Employee> employees;

	public OrganizationRole() {

	}

	@Override
	public boolean isNew() {
		return true;
	}

}
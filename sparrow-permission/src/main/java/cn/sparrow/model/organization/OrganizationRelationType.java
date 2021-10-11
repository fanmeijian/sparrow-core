package cn.sparrow.model.organization;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import cn.sparrow.model.permission.AbstractOperationLog;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 4:56:59 PM
 */

@Getter
@Setter
@Entity
@Table(name = "spr_organization_relation_type")
public class OrganizationRelationType extends AbstractOperationLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	private String id;

	private String code;
	private String name;
	private String stat;
	
	@OneToMany(mappedBy = "organizationRelationType")
	private Set<OrganizationRelation> organizationRelations;

	public OrganizationRelationType() {

	}

}
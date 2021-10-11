package cn.sparrow.model.organization;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import cn.sparrow.model.permission.AbstractOperationLog;
import lombok.Data;

@Data
@Entity
@Table(name = "spr_organization_type")
public class OrganizationType extends AbstractOperationLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	private String id;
	
	private String name;
	private String code;
	
	@OneToMany(mappedBy = "type")
	Set<Organization> organizations;

}

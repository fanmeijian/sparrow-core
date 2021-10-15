package cn.sparrow.model.organization;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.common.Group;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spr_role")
public class Role extends AbstractSparrowEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;

	@ManyToMany(mappedBy = "roles")
	private Set<Organization> organizations;
	
	@ManyToMany(mappedBy = "roles")
	private Set<Group> groups;
	
	private String stat;

	public Role() {

	}

}
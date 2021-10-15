package cn.sparrow.model.organization;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.common.Group;
import lombok.Getter;
import lombok.Setter;

/**
 * ְ��
 * 
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 4:56:09 PM
 */

@Getter
@Setter
@Entity
@Table(name = "spr_level")
public class Level extends AbstractSparrowEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String stat;

	@ManyToMany(mappedBy = "levels")
	private Set<Organization> organizations;
	
	@ManyToMany(mappedBy = "levels")
	private Set<Group> groups;

	public Level() {

	}

}
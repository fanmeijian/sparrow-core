package cn.sparrow.model.organization;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.group.Group;
import lombok.Getter;
import lombok.Setter;


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
	private boolean root;

	// use for create relation at batch
	@Transient
	@JsonProperty
	private String parentId;
	
	@ManyToMany(mappedBy = "levels")
	private Set<Organization> organizations;
	
	@ManyToMany(mappedBy = "levels")
	private Set<Group> groups;

	public Level() {

	}

}
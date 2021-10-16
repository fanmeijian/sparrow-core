package cn.sparrow.model.permission;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.group.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_sysrole")
public class Sysrole extends AbstractSparrowEntity {
	private static final long serialVersionUID = 1L;

	private String name;
	private String code;

	@EqualsAndHashCode.Exclude
	@ManyToMany
	@JoinTable(name = "spr_sysrole_menu", joinColumns = { @JoinColumn(name = "SYSROLE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "MENU_ID") })
	private Set<Menu> menus;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(mappedBy = "sysroles")
	private Set<Group> groups;

	public Sysrole(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}
	
	

}

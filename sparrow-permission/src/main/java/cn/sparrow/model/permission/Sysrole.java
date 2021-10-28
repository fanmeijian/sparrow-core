package cn.sparrow.model.permission;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import cn.sparrow.model.common.AbstractOperationLog;
import cn.sparrow.model.group.GroupSysrole;
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
public class Sysrole extends AbstractOperationLog {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	protected String id;
	
	private String name;
	private String code;
	private boolean isSystem;

	@EqualsAndHashCode.Exclude
	@ManyToMany
	@JoinTable(name = "spr_sysrole_menu", joinColumns = { @JoinColumn(name = "SYSROLE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "MENU_ID") })
	private Set<Menu> menus;
	
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = GroupSysrole.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysrole")
	private Set<GroupSysrole> groupSysroles;
	
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.LAZY)
	private Set<SparrowUrl> urls;

	public Sysrole(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}
	
	

}

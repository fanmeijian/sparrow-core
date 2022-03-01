package cn.sparrow.model.permission;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = false,onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name="spr_sysrole_menu")
@NamedQuery(name="SysroleMenu.findAll", query="SELECT s FROM SysroleMenu s")
public class SysroleMenu extends AbstractOperationLog {
	

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@EmbeddedId
	private SysroleMenuPK id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_id", insertable = false, updatable = false)
	private Menu menu;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sysrole_id", insertable = false, updatable = false)
	private Sysrole sysrole;
	
	//是否包含所有子单，如果勾选了，则如果有新加子菜单，则会自动授予该角色
	private Boolean includeSubMenu;
	
	public SysroleMenu(SysroleMenuPK sysroleMenuPK) {
		this.id = sysroleMenuPK;
	}

}
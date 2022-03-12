package cn.sparrow.permission.model.resource;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_sysrole_menu")
@NamedQuery(name = "SysroleMenu.findAll", query = "SELECT s FROM SysroleMenu s")
@Audited
public class SysroleMenu extends AbstractSparrowEntity {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@EmbeddedId
	@JoinColumns({ @JoinColumn(name = "sysrole_id"), @JoinColumn(name = "menu_id"), })
	private SysroleMenuPK id;

	@NotAudited
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_id", insertable = false, updatable = false)
	private Menu menu;

	@NotAudited
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sysrole_id", insertable = false, updatable = false)
	private Sysrole sysrole;

	// 是否包含所有子单，如果勾选了，则如果有新加子菜单，则会自动授予该角色
	private Boolean includeSubMenu = false;

	public SysroleMenu(SysroleMenuPK sysroleMenuPK) {
		this.id = sysroleMenuPK;
	}

	public SysroleMenu(String menuId, String sysroleId) {
		this.id = new SysroleMenuPK(sysroleId, menuId);
	}

}
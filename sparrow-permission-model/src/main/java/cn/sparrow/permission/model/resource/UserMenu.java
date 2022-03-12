package cn.sparrow.permission.model.resource;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_user_menu")
public class UserMenu extends AbstractSparrowEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@EmbeddedId
	private UserMenuPK id;
	
	@ManyToOne
	@JoinColumn(name = "menu_id", insertable = false, updatable = false)
	private Menu menu;
	
	//是否包含所有子单，如果勾选了，则如果有新加子菜单，则会自动授予该用户
	private Boolean includeSubMenu = false;

	public UserMenu(UserMenuPK id) {
		super();
		this.id = id;
	}
	
	public UserMenu(String menuId, String username) {
		super();
		this.id = new UserMenuPK(username, menuId);
	}

}

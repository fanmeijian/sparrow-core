package cn.sparrow.model.menu;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;
import cn.sparrow.model.permission.AbstractOperationLog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spr_user_menu")
@NamedQuery(name = "UserMenu.findAll", query = "SELECT s FROM UserMenu s")
public class UserMenu extends AbstractOperationLog implements Persistable<UserMenuPK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private UserMenuPK id;

	public UserMenu(UserMenuPK id) {
		super();
		this.id = id;
	}
	
	public UserMenu() {
		super();
	}

	// 用于避免调用save和saveall方法的时候，会先执行select语句
	@Override
	public boolean isNew() {
		return true;
	}

}

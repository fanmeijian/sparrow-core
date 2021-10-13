package cn.sparrow.model.menu;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.data.domain.Persistable;
import cn.sparrow.model.permission.AbstractOperationLog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spr_user_menu")
public class UserMenu extends AbstractOperationLog implements Persistable<UserMenuPK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private UserMenuPK id;
	
	@ManyToOne
	@JoinColumn(name = "menu_id", insertable = false, updatable = false)
	private Menu menu;

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

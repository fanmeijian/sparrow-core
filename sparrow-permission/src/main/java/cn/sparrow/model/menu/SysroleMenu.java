package cn.sparrow.model.menu;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import cn.sparrow.model.permission.AbstractOperationLog;
import lombok.Data;


@Data
@Entity
@Table(name="spr_sysrole_menu")
@NamedQuery(name="SysroleMenu.findAll", query="SELECT s FROM SysroleMenu s")
public class SysroleMenu extends AbstractOperationLog {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SysroleMenuPK id;

	@ManyToOne
	@JoinColumn(name = "menu_id", insertable = false, updatable = false)
	private Menu menu;

}
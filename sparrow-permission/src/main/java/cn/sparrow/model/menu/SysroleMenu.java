package cn.sparrow.model.menu;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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


}
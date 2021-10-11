package cn.sparrow.model.sysrole;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import cn.sparrow.model.permission.AbstractOperationLog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spr_user_sysrole")
@NamedQuery(name = "UserSysrole.findAll", query = "SELECT s FROM UserSysrole s")
public class UserSysrole extends AbstractOperationLog {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserSysrolePK id;

	public UserSysrole() {
	}

}
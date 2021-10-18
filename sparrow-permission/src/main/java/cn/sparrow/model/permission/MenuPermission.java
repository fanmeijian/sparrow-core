package cn.sparrow.model.permission;

import java.util.List;

import lombok.Data;

@Data
public class MenuPermission {
	private List<UserMenuPK> userMenuPKs;
	private List<SysroleMenuPK> sysroleMenuPKs;
}

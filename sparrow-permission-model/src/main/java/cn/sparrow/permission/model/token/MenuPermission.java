package cn.sparrow.permission.model.token;

import java.util.List;

import cn.sparrow.permission.model.resource.SysroleMenuPK;
import cn.sparrow.permission.model.resource.UserMenuPK;
import lombok.Data;

@Data
public class MenuPermission {
  private List<UserMenuPK> userMenuPKs;
  private List<SysroleMenuPK> sysroleMenuPKs;
}

package cn.sparrow.permission.service;

import java.util.List;

import cn.sparrow.model.permission.SysroleMenuPK;
import cn.sparrow.model.permission.UserMenuPK;
import lombok.Data;

@Data
public class MenuPermission {
  private List<UserMenuPK> userMenuPKs;
  private List<SysroleMenuPK> sysroleMenuPKs;
}

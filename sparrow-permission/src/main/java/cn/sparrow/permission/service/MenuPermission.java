package cn.sparrow.permission.service;

import java.util.List;

import cn.sparrow.permission.model.SysroleMenuPK;
import cn.sparrow.permission.model.UserMenuPK;
import lombok.Data;

@Data
public class MenuPermission {
  private List<UserMenuPK> userMenuPKs;
  private List<SysroleMenuPK> sysroleMenuPKs;
}

package cn.sparrow.common.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sparrow.common.repository.UserMenuRepository;
import cn.sparrow.common.repository.UserSysroleRepository;
import cn.sparrow.model.menu.UserMenu;
import cn.sparrow.model.menu.UserMenuPK;
import cn.sparrow.model.sysrole.PreserveSysroleEnum;
import cn.sparrow.model.sysrole.UserSysrole;
import cn.sparrow.model.sysrole.UserSysrolePK;

@Service
public class UserService {

  @Autowired UserMenuRepository userMenuRepository;
  @Autowired UserSysroleRepository userSysroleRepository;
  
  private static Logger logger = LoggerFactory.getLogger(UserService.class);

  public void removeMenusByMenuId(String username, List<String> menuIds) {
    userMenuRepository.deleteByIdUsernameAndIdMenuIdIn(username, menuIds);
  }

  public void addMenusByMenuId(String username, List<String> menuIds) {
    Set<UserMenu> userMenus = new HashSet<UserMenu>();
    menuIds.forEach(f -> {
      userMenus.add(new UserMenu(new UserMenuPK(username, f)));
    });
    addMenus(userMenus);
  }

  public void addMenusByUserMenuPK(Set<UserMenuPK> userMenuPKs) {
    Set<UserMenu> userMenus = new HashSet<UserMenu>();
    userMenuPKs.forEach(f -> {
      userMenus.add(new UserMenu(f));
    });
    addMenus(userMenus);
  }

  public void addMenus(Set<UserMenu> userMenus) {
    userMenuRepository.saveAll(userMenus);
  }
  
  public void init(String username) {
    userSysroleRepository.save(new UserSysrole(new UserSysrolePK(username,PreserveSysroleEnum.SYSADMIN.name())));
    logger.info("Grant user {} with role", username, PreserveSysroleEnum.SYSADMIN.name());
  }

}

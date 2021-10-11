package cn.sparrow.common.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sparrow.common.repository.UserMenuRepository;
import cn.sparrow.model.menu.UserMenu;
import cn.sparrow.model.menu.UserMenuPK;
import cn.sparrow.organization.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  UserMenuRepository userMenuRepository;

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

}

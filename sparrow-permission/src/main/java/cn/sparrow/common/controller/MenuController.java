package cn.sparrow.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.sparrow.common.service.MenuService;
import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.menu.Menu;

@RestController
public class MenuController {

  @Autowired MenuService menuService;
  
  @GetMapping("/menus/menuTree")
  public MyTree<Menu> menuTree(@Param("parentId") String parentId) {
      return menuService.getTreeByParentId(parentId);
  }

  @GetMapping("/menus/userMenuTree")
  public MyTree<Menu> getTreeByUsername(@Param("username") String username) {
      return menuService.getTreeByUsername(username);
  }

  @GetMapping("/menus/sysroleMenuTree")
  public MyTree<Menu> getTreeBySysroleId(@Param("sysroleId") String sysroleId) {
      return menuService.getTreeBySysroleId(sysroleId);
  }
}

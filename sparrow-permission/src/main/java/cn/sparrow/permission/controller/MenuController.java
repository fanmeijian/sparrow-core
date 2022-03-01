package cn.sparrow.permission.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.sparrow.permission.model.Menu;
import cn.sparrow.permission.model.SparrowTree;
import cn.sparrow.permission.model.SysroleMenu;
import cn.sparrow.permission.repository.MenuRepository;
import cn.sparrow.permission.service.MenuPermission;
import cn.sparrow.permission.service.MenuService;

@RestController
public class MenuController {

  @Autowired
  MenuService menuService;
  @Autowired
  MenuRepository menuRepository;

  // @Resource
  // private RepositoryEntityLinks entityLinks;

  @GetMapping("/menus/getModelName")
  public String getModelName() {
    return "{\"modelName\":\"" + Menu.class.getName() + "\"}";
  }

  @PostMapping("/menus/setPosition")
  public void setPosition(@RequestBody Menu menu) {
    menuService.setPosition(menu);
  }

  @GetMapping("/menus/getTreeByParentId")
  public SparrowTree<Menu, String> getTreeByParentId(@Nullable @Param("parentId") String parentId) {
    return menuService.getTreeByParentId(parentId);
  }

  @GetMapping("/menus/getTreeByUsername")
  public SparrowTree<Menu, String> getTreeByUsername(@NotNull @Param("username") String username) {
    return menuService.getTreeByUsername(username);
  }

  @GetMapping("/menus/getTreeBySysroleId")
  public SparrowTree<Menu, String> getTreeBySysroleId(
      @NotNull @Param("sysroleId") String sysroleId) {
    return menuService.getTreeBySysroleId(sysroleId);
  }

  @GetMapping("/menus/getMyTree")
  public SparrowTree<Menu, String> getMyTree(Principal principal) {
    return menuService.getTreeByUsername(principal.getName());
  }

  @GetMapping("/menus/sysroles")
  public Set<SysroleMenu> getSysroleMenus(@NotBlank @RequestParam("menuId") String menuId){
    return menuService.getSysroleMenus(menuId);
  }

  // for repository resource controller
  // @GetMapping("/menus/getMyTree")
  // public ResponseEntity<?> getMyTree(Principal principal) {
  // EntityModel<SparrowTree<Menu, String>> resource = new
  // EntityModel<>(menuService.getTreeByUsername(principal.getName()));
  // return ResponseEntity.ok(resource);
  // }


//  @PostMapping("/menus/batch")
//  public void add(@NotNull @RequestBody final List<Menu> menus) {
//    menuRepository.saveAll(menus);
//  }
//
//  @PatchMapping("/menus/batch")
//  public void update(@NotNull @RequestBody final List<Menu> menus) {
//    menuRepository.saveAll(menus);
//  }

  @DeleteMapping("/menus/batch")
  public void delete(@NotNull @RequestBody final String[] ids) {
    menuRepository.deleteByIdIn(ids);
  }

  @PostMapping("/menus/permissions")
  public void addPermission(@NotNull @RequestBody final MenuPermission menuPermission) {
    menuService.addPermissions(menuPermission);
  }

  @DeleteMapping("/menus/permissions")
  public void delPermission(@NotNull @RequestBody final MenuPermission menuPermission) {
    menuService.delPermissions(menuPermission);
  }

}

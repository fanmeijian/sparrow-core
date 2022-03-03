package cn.sparrow.permission.service;

import java.security.Principal;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import cn.sparrow.permission.model.Menu;
import cn.sparrow.permission.model.SparrowTree;
import cn.sparrow.permission.model.SysroleMenu;

public interface MenuService {
	public void setPosition(@RequestBody Menu menu);

	@GetMapping("/menus/getTreeByParentId")
	public SparrowTree<Menu, String> getTreeByParentId(@Nullable @Param("parentId") String parentId);

	@GetMapping("/menus/getTreeByUsername")
	public SparrowTree<Menu, String> getTreeByUsername(@NotNull @Param("username") String username);

	@GetMapping("/menus/getTreeBySysroleId")
	public SparrowTree<Menu, String> getTreeBySysroleId(@NotNull @Param("sysroleId") String sysroleId);

	@GetMapping("/menus/getMyTree")
	public SparrowTree<Menu, String> getMyTree(Principal principal);

	@GetMapping("/menus/sysroles")
	public Set<SysroleMenu> getSysroleMenus(@NotBlank @RequestParam("menuId") String menuId);

	@DeleteMapping("/menus/batch")
	public void delete(@NotNull @RequestBody final String[] ids);

	@PostMapping("/menus/permissions")
	public void addPermission(@NotNull @RequestBody final MenuPermission menuPermission);

	@DeleteMapping("/menus/permissions")
	public void delPermission(@NotNull @RequestBody final MenuPermission menuPermission);
}

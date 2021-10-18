package cn.sparrow.common.controller;

import java.util.List;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.permission.Menu;
import cn.sparrow.model.permission.MenuPermission;
import cn.sparrow.permission.service.MenuService;

@RestController
public class MenuController {

	@Autowired MenuService menuService;

	@GetMapping("/menus/getTreeByParentId")
	public MyTree<Menu> getTreeByParentId(@Nullable @Param("parentId") String parentId) {
		return menuService.getTreeByParentId(parentId);
	}

	@GetMapping("/menus/getTreeByUsername")
	public MyTree<Menu> getTreeByUsername(@NotNull @Param("username") String username) {
		return menuService.getTreeByUsername(username);
	}

	@GetMapping("/menus/getTreeBySysroleId")
	public MyTree<Menu> getTreeBySysroleId(@NotNull @Param("sysroleId") String sysroleId) {
		return menuService.getTreeBySysroleId(sysroleId);
	}
	
	@GetMapping("/menus/getMyTree")
	public MyTree<Menu> getMyTree() {
		return null;
	}

	@PostMapping("/menus/batch")
	public void add(@NotNull @RequestBody final List<Menu> urls) {

	}

	@PatchMapping("/menus/batch")
	public void update(@NotNull @RequestBody final List<Menu> urls) {

	}

	@DeleteMapping("/menus/batch")
	public void delete(@NotNull @RequestBody final List<String> urls) {

	}

	@PostMapping("/menus/permissions")
	public void addPermission(@NotNull @RequestBody final MenuPermission menuPermission) {

	}

	@DeleteMapping("/menus/permissions")
	public void delPermission(@NotNull @RequestBody final MenuPermission menuPermission) {

	}

}

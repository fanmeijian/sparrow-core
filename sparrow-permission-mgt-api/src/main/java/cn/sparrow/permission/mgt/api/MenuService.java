package cn.sparrow.permission.mgt.api;

import java.security.Principal;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import cn.sparrow.permission.model.resource.Menu;
import cn.sparrow.permission.model.resource.SparrowTree;
import cn.sparrow.permission.model.resource.Sysrole;
import cn.sparrow.permission.model.token.MenuPermission;

public interface MenuService extends MenuRestService {

//	@GetMapping("/getTreeByParentId")
//	@Operation(summary = "获取菜单树")
//	@ResponseBody
	public SparrowTree<Menu, String> getTreeByParentId(@Nullable @RequestParam("parentId") String parentId);

//	@GetMapping("/getTreeByUsername")
//	@Operation(summary = "获取用户菜单树")
//	@ResponseBody
	public SparrowTree<Menu, String> getTreeByUsername(@NotNull @RequestParam("username") String username);

//	@GetMapping("/getTreeBySysroleId")
//	@Operation(summary = "获取角色菜单树")
//	@ResponseBody
	public SparrowTree<Menu, String> getTreeBySysroleId(@NotNull @RequestParam("sysroleId") String sysroleId);

	public SparrowTree<Menu, String> getMyTree(Principal principal);

//	@GetMapping("/{menuId}/sysroles")
//	@Operation(summary = "获取菜单的授权角色")
//	@ResponseBody
	public List<Sysrole> getSysroles(@PathVariable("menuId") String menuId);

//	@GetMapping("/{menuId}/users")
//	@Operation(summary = "获取菜单的授权用户")
//	@ResponseBody
	public List<String> getUsers(@PathVariable("menuId") String menuId);

//	@PostMapping("/permissions")
//	@Operation(summary = "设置菜单权限")
//	@ResponseBody
	public void addPermission(@NotNull @RequestBody final MenuPermission menuPermission);

//	@DeleteMapping("/permissions")
//	@Operation(summary = "取消菜单权限")
//	@ResponseBody
	public void delPermission(@NotNull @RequestBody final MenuPermission menuPermission);
}

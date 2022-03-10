package cn.sparrow.permission.mgt.api;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.model.resource.Menu;
import cn.sparrow.permission.model.resource.SparrowTree;
import cn.sparrow.permission.model.resource.Sysrole;
import cn.sparrow.permission.model.resource.SysroleMenu;
import cn.sparrow.permission.model.token.MenuPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "菜单服务")
@RequestMapping("/menus")
public interface MenuService {

	@PostMapping("")
	@Operation(summary = "新增菜单")
	@ResponseBody
	public Menu save(@RequestBody Menu menu);

	@DeleteMapping("")
	@Operation(summary = "删除菜单")
	@ResponseBody
	public void delete(@NotNull @RequestBody final String[] ids);

	@PatchMapping(path = "/{menuId}", consumes = "application/json-patch+json")
	@Operation(summary = "更新菜单")
	@ResponseBody
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = Menu.class)))
	public Menu update(@PathVariable("menuId") String menuId, @RequestBody Map<String, Object> map);

	@GetMapping("")
	@Operation(summary = "浏览菜单")
	@ResponseBody
	public Page<Menu> all(Pageable pageable, Menu menu);

	@GetMapping("/getTreeByParentId")
	@Operation(summary = "获取菜单树")
	@ResponseBody
	public SparrowTree<Menu, String> getTreeByParentId(@Nullable @RequestParam("parentId") String parentId);

	@GetMapping("/getTreeByUsername")
	@Operation(summary = "获取用户菜单树")
	@ResponseBody
	public SparrowTree<Menu, String> getTreeByUsername(@NotNull @RequestParam("username") String username);

	@GetMapping("/getTreeBySysroleId")
	@Operation(summary = "获取角色菜单树")
	@ResponseBody
	public SparrowTree<Menu, String> getTreeBySysroleId(@NotNull @RequestParam("sysroleId") String sysroleId);

	@Operation(summary = "获取当前用户菜单树")
	@ResponseBody
	@GetMapping("/getMyTree")
	public SparrowTree<Menu, String> getMyTree(Principal principal);

	@GetMapping("/sysroles")
	@Operation(summary = "获取菜单的授权角色")
	@ResponseBody
	public List<Sysrole> getSysroles(String menuId);

	@GetMapping("/users")
	@Operation(summary = "获取菜单的授权用户")
	@ResponseBody
	public List<String> getUsers(String menuId);

	@PostMapping("/permissions")
	@Operation(summary = "设置菜单权限")
	@ResponseBody
	public void addPermission(@NotNull @RequestBody final MenuPermission menuPermission);

	@DeleteMapping("/permissions")
	@Operation(summary = "取消菜单权限")
	@ResponseBody
	public void delPermission(@NotNull @RequestBody final MenuPermission menuPermission);

	@PatchMapping("/{menuId}/sort")
	@Operation(summary = "菜单排序")
	@ResponseBody
	public void setPosition(@PathVariable("menuId") String menuId, @RequestParam("prevId") String prevId,
			@RequestParam("nextId") String nextId);
}

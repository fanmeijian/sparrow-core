package cn.sparrow.permission.mgt.api;

import java.security.Principal;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.constant.MenuPermissionTargetEnum;
import cn.sparrow.permission.constant.MenuTreeTypeEnum;
import cn.sparrow.permission.model.resource.Menu;
import cn.sparrow.permission.model.resource.SparrowTree;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "菜单服务")
@RequestMapping("/menus")
public interface MenuRestService {

	@PostMapping("")
	@Operation(summary = "新增菜单")
	@ResponseBody
	public Menu save(@RequestBody Menu menu);

	@Operation(summary = "菜单树")
	@ResponseBody
	@GetMapping("/tree")
	public SparrowTree<Menu, String> getTree(MenuTreeTypeEnum type, @Nullable String sysroleId,
			@Nullable String username, @Nullable String parentId, Principal principal);

	@GetMapping("/{menuId}/permissions")
	@Operation(summary = "浏览授权")
	@ResponseBody
	public List<?> getPermissions(@PathVariable("menuId") String menuId, MenuPermissionTargetEnum type);

	@PostMapping("/{menuId}/permissions")
	@Operation(summary = "设置菜单权限")
	@ResponseBody
	public void addPermission(@PathVariable("menuId") String menuId, MenuPermissionTargetEnum type,
			@NotNull @RequestBody List<String> permissions);

	@DeleteMapping("/{menuId}/permissions")
	@Operation(summary = "取消菜单权限")
	@ResponseBody
	public void delPermission(@PathVariable("menuId") String menuId, MenuPermissionTargetEnum type,
			@NotNull @RequestBody List<String> permissions);
}

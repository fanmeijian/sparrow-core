package cn.sparrow.permission.mgt.api;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sparrow.permission.constant.MenuPermissionTargetEnum;
import cn.sparrow.permission.constant.MenuTreeTypeEnum;
import cn.sparrow.permission.model.resource.Menu;
import cn.sparrow.permission.model.resource.SparrowTree;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "menu", description = "菜单服务")
@RequestMapping("/menus")
public interface MenuRestService {

	@PostMapping("")
	@Operation(summary = "新增菜单")
	@ResponseBody
	public Menu save(@RequestBody Menu menu);

	@GetMapping("/{menuId}")
	@Operation(summary = "菜单详情")
	@ResponseBody
	public Menu get(@PathVariable("menuId") String menuId);

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

	@PutMapping("/{menuId}/permissions/delete")
	@Operation(summary = "取消菜单权限")
	@ResponseBody
	public void delPermission(@PathVariable("menuId") String menuId, MenuPermissionTargetEnum type,
			@NotNull @RequestBody List<String> permissions);
	
	@PutMapping("/delete")
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
	public Page<Menu> all(@Nullable Pageable pageable,@Nullable Menu menu);
	
	@PatchMapping("/{menuId}/sort")
	@Operation(summary = "菜单排序")
	@ResponseBody
	public void setPosition(@PathVariable("menuId") String menuId, @RequestParam("prevId") String prevId,
			@RequestParam("nextId") String nextId);
}

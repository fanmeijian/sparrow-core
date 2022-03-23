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

	public SparrowTree<Menu, String> getTreeByParentId(@Nullable @RequestParam("parentId") String parentId);

	public SparrowTree<Menu, String> getTreeByUsername(@NotNull @RequestParam("username") String username);

	public SparrowTree<Menu, String> getTreeBySysroleId(@NotNull @RequestParam("sysroleId") String sysroleId);

	public SparrowTree<Menu, String> getMyTree(Principal principal);

	public List<Sysrole> getSysroles(@PathVariable("menuId") String menuId);

	public List<String> getUsers(@PathVariable("menuId") String menuId);

	public void addPermission(@NotNull @RequestBody final MenuPermission menuPermission);

	public void delPermission(@NotNull @RequestBody final MenuPermission menuPermission);
}

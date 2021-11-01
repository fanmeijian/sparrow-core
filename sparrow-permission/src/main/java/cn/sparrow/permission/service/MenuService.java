package cn.sparrow.permission.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sparrow.common.repository.UserMenuRepository;
import cn.sparrow.common.service.ISparrowSortedService;
import cn.sparrow.model.common.SparrowSortableTree;
import cn.sparrow.model.permission.Menu;
import cn.sparrow.model.permission.MenuPermission;
import cn.sparrow.model.permission.SysroleMenu;
import cn.sparrow.model.permission.UserMenu;
import cn.sparrow.permission.repository.MenuRepository;
import cn.sparrow.permission.repository.SysroleMenuRepository;

@Service
public class MenuService {
	private static Logger logger = LoggerFactory.getLogger(MenuService.class);

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	UserMenuRepository userMenuRepository;

	@Autowired
	SysroleMenuRepository sysroleMenuRepository;

	@Autowired
	ISparrowSortedService<Menu, String> sparrowSortedService;

	@Autowired
	SysroleService sysroleService;

	public SparrowSortableTree<Menu, String> getTreeByParentId(String parentId) {
		Menu menu = menuRepository.findById(parentId).orElse(new Menu(null, null));
		SparrowSortableTree<Menu, String> menuTree = new SparrowSortableTree<Menu, String>(menu, menu.getId(),menu.getNextNodeId(), menu.getNextNodeId());
		buildTree(menuTree);
		return menuTree;
	}

	public void buildTree(SparrowSortableTree<Menu, String> menuTree) {
		List<Menu> menus = menuRepository.findByParentId(menuTree.getMe().getId());
		// sort the menus
//        sparrowSortedService.sort(menus);

		for (Menu menu : menus) {
			SparrowSortableTree<Menu, String> leaf = new SparrowSortableTree<Menu, String>(menu, menu.getId(),menu.getNextNodeId(), menu.getNextNodeId());
			menuTree.getSortableChildren().add(leaf);
			buildTree(leaf);
		}
	}

	public void buildTree(SparrowSortableTree<Menu, String> menuTree, List<Menu> userMenus) {
		List<Menu> menus = menuRepository.findByParentId(menuTree.getMe().getId());
		// sort the menus
//        sparrowSortedService.sort(menus);

		for (Menu menu : menus) {
			SparrowSortableTree<Menu, String> leaf = new SparrowSortableTree<Menu, String>(menu, menu.getId(),menu.getNextNodeId(), menu.getNextNodeId());
			if (userMenus.stream().anyMatch(p -> p.getId().equals(menu.getId())))
				menuTree.getSortableChildren().add(leaf);
			buildTree(leaf, userMenus);
		}
	}

	public SparrowSortableTree<Menu, String> getTreeByUsername(String username) {
		SparrowSortableTree<Menu, String> menuTree = new SparrowSortableTree<Menu, String>(new Menu());
		buildUserTree(username, menuTree);
		sparrowSortedService.sort(menuTree);
		return menuTree;
	}

	public SparrowSortableTree<Menu, String> getTreeBySysroleId(String sysroleId) {
		SparrowSortableTree<Menu, String> menuTree = new SparrowSortableTree<Menu, String>(new Menu());
		buildSysroleTree(sysroleId, menuTree);
		return menuTree;
	}

	// 构建角色菜单的大树，含直接父级的菜单
	public void buildSysroleTree(String sysroleId, SparrowSortableTree<Menu, String> menuTree) {
		List<Menu> menusSet = new ArrayList<Menu>();
		getSysroleMenusWithParentAndChildren(sysroleId, menusSet);

		// 构建用户的菜单树
		List<Menu> menus = menuRepository.findByParentId(menuTree.getMe().getId());
		for (Menu menu : menus) {
			SparrowSortableTree<Menu, String> leaf = new SparrowSortableTree<Menu, String>(menu, menu.getId(),menu.getNextNodeId(), menu.getNextNodeId());
			if (menusSet.stream().anyMatch(p -> p.getId().equals(menu.getId())))
				menuTree.getSortableChildren().add(leaf);
			buildTree(leaf, menusSet);
		}
	}

	// 构建用户菜单的大树，含直接父级的菜单
	public void buildUserTree(String username, SparrowSortableTree<Menu, String> menuTree) {
		List<Menu> menusSet = new ArrayList<Menu>();
		getUserMenusWithParentAndChildren(username, menusSet);
		// 整合用户拥有角色的菜单
		sysroleService.getUserSysroles(username).forEach(userSysrole -> {
			getSysroleMenusWithParentAndChildren(userSysrole.getId().getSysroleId(), menusSet);
		});

		// 构建用户的菜单树
		List<Menu> menus = menuRepository.findByParentId(menuTree.getMe().getId());
		for (Menu menu : menus) {
			SparrowSortableTree<Menu, String> leaf = new SparrowSortableTree<Menu, String>(menu, menu.getId(),menu.getNextNodeId(), menu.getNextNodeId());
			if (menusSet.stream().anyMatch(p -> p.getId().equals(menu.getId())))
				menuTree.getSortableChildren().add(leaf);
			buildTree(leaf, menusSet);
		}
	}

	// 获取用户菜单的亲戚集合（不含兄弟姐妹节点）
	public void getUserMenusWithParentAndChildren(String username, List<Menu> menus) {
		// menus.addAll(userRepository.findById(username).get().getMenus()) ;
		userMenuRepository.findByIdUsername(username).forEach(f -> {
			menus.add(f.getMenu());
			buildParents(f.getMenu().getParentId(), menus);
			buildChildren(f.getMenu().getId(), menus);
		});
	}

	// 获取角色菜单的亲戚集合（不含兄弟姐妹节点）
	public void getSysroleMenusWithParentAndChildren(String sysroleId, List<Menu> menus) {
		// menus.addAll(userRepository.findById(username).get().getMenus()) ;
		sysroleMenuRepository.findByIdSysroleId(sysroleId).forEach(f -> {
			menus.add(f.getMenu());
			buildParents(f.getMenu().getParentId(), menus);
			buildChildren(f.getMenu().getId(), menus);
		});
	}

	// 获取到所有的祖先集合
	public void buildParents(String parentId, List<Menu> menus) {

		if (parentId != null) {
			Menu parent = menuRepository.findById(parentId).orElse(null);
			menus.add(parent);
			buildParents(parent.getParentId(), menus);
		}
	}

	// 获取到所有的子孙集合
	public void buildChildren(String menuId, List<Menu> menus) {
		menuRepository.findByParentId(menuId).forEach(f -> {
			menus.add(f);
			buildChildren(f.getId(), menus);
		});
	}

	public void addPermissions(MenuPermission menuPermission) {
		if (menuPermission.getUserMenuPKs() != null) {
			menuPermission.getUserMenuPKs().forEach(f -> {
				userMenuRepository.save(new UserMenu(f));
			});
		}

		if (menuPermission.getSysroleMenuPKs() != null) {
			menuPermission.getSysroleMenuPKs().forEach(f -> {
				sysroleMenuRepository.save(new SysroleMenu(f));
			});
		}
	}

	public void delPermissions(MenuPermission menuPermission) {
		if (menuPermission.getUserMenuPKs() != null) {
			userMenuRepository.deleteByIdIn(menuPermission.getUserMenuPKs());
		}

		if (menuPermission.getSysroleMenuPKs() != null) {
			sysroleMenuRepository.deleteByIdIn(menuPermission.getSysroleMenuPKs());
		}
	}

	public void setPosition(Menu menu) {
		sparrowSortedService.saveSort(menuRepository, menu);
	}

}

package cn.sparrow.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.sparrow.common.repository.UrlRepository;
import cn.sparrow.common.service.MenuService;
import cn.sparrow.common.service.SparrowService;
import cn.sparrow.common.service.UserService;
import cn.sparrow.model.menu.Menu;
import cn.sparrow.model.menu.MyTree;
import cn.sparrow.model.permission.Model;
import cn.sparrow.model.permission.ModelAttribute;
import cn.sparrow.model.permission.ModelAttributePK;
import cn.sparrow.permission.repository.ModelAttributeRepository;
import cn.sparrow.permission.repository.ModelRepository;

@RestController
public class SparrowController {

	@Autowired
	MenuService menuService;
	@Autowired
	SparrowService sparrowService;
	@Autowired
	UserService userService;

	@Autowired
	ModelRepository modelRepository;

	@Autowired
	ModelAttributeRepository modelAttributeRepository;

	@Autowired
	UrlRepository urlRepository;

	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	private ApplicationContext applicationContext;

	@GetMapping("/init")
	public void init() {
		sparrowService.init();
	}

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

	@PatchMapping("/users/{username}/removeMenus")
	public void removeMenus(@PathVariable("username") String username, @RequestBody final List<String> menuIds) {
		userService.removeMenusByMenuId(username, menuIds);
	}

	@PatchMapping("/users/{username}/addMenus")
	public void addMenus(@PathVariable("username") String username, @RequestBody final List<String> menuIds) {
		userService.addMenusByMenuId(username, menuIds);
	}

	@GetMapping("/models/syncToTable")
	public Map<String, List<String>> syncToTable() {
		Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();
		Map<String, List<String>> entitiesMap = new HashMap<String, List<String>>();

		entityTypes.forEach(e -> {
			Model model = modelRepository.save(new Model(e.getJavaType().getName()));

			List<String> attributes = new ArrayList<String>();
			e.getAttributes().forEach(a -> {
				attributes.add(a.getName());

				modelAttributeRepository.save(new ModelAttribute(new ModelAttributePK(a.getName(), model.getName()),
						a.getJavaType().getName()));

			});
			entitiesMap.put(e.getJavaType().getName(), attributes);

		});
		return entitiesMap;

	}

	@GetMapping("/models/allEntities")
	public Map<String, Object> allEntities() {
		Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Table.class);
		return beans;
	}

}

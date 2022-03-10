package cn.sparrow.permission.mgt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;

import cn.sparrow.permission.constant.ApiPermissionEnum;
import cn.sparrow.permission.constant.HttpMethodEnum;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.core.service.PermissionServiceImpl;
import cn.sparrow.permission.mgt.service.impl.ApiServiceImpl;
import cn.sparrow.permission.mgt.service.impl.MenuServiceImpl;
import cn.sparrow.permission.mgt.service.impl.SysroleServiceImpl;
import cn.sparrow.permission.mgt.service.repository.ApiRepository;
import cn.sparrow.permission.mgt.service.repository.MenuRepository;
import cn.sparrow.permission.mgt.service.repository.SysroleRepository;
import cn.sparrow.permission.mgt.service.repository.UserSysroleRepository;
import cn.sparrow.permission.model.resource.Menu;
import cn.sparrow.permission.model.resource.SparrowApi;
import cn.sparrow.permission.model.resource.SparrowTree;
import cn.sparrow.permission.model.resource.Sysrole;
import cn.sparrow.permission.model.resource.SysroleApiPK;
import cn.sparrow.permission.model.resource.SysroleMenuPK;
import cn.sparrow.permission.model.resource.UserMenuPK;
import cn.sparrow.permission.model.resource.UserSysrole;
import cn.sparrow.permission.model.resource.UserSysrolePK;
import cn.sparrow.permission.model.token.MenuPermission;
import lombok.extern.slf4j.Slf4j;

// @SpringJUnitConfig
// @SpringBootTest
// @EnableAutoConfiguration
// @ExtendWith(SpringRunner.class)
// @ContextConfiguration(classes = {SparrowApi.class})
// @ExtendWith(SpringExtension.class)

@Slf4j
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class ResourceTests {
	@Autowired
	private static TestEntityManager entityManager;

	@Autowired
	ApiRepository apiRepository;
	@Autowired
	SysroleRepository sysroleRepository;

	@Autowired
	SysroleServiceImpl sysroleService;

	@Autowired
	ApiServiceImpl apiService;
	@Autowired
	MenuServiceImpl menuServiceImpl;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	SysroleServiceImpl sysroleServiceImpl;

	@Autowired UserSysroleRepository userSysroleRepository;


	@TestConfiguration
    static class PermissionServiceImplTestContextConfiguration {
        @Bean
        public PermissionService permissionService() {
            return new PermissionServiceImpl((EntityManager) entityManager);
        }
    }

	@Test
	void apiTest() throws Exception {
		Sysrole sysrole = new Sysrole("test", "test");
		assertNotNull(sysroleService.create(sysrole));
		SparrowApi sparrowApi = new SparrowApi("-","-",HttpMethodEnum.GET,"",ApiPermissionEnum.AUTHENTICATED);
		assertNotNull(apiService.create(sparrowApi));
		List<SysroleApiPK> sysroleApiPKs =new ArrayList<>();
		sysroleApiPKs.add(new SysroleApiPK(sysrole.getId(), sparrowApi.getId()));
		apiService.addPermissions(sysroleApiPKs);
		log.info("add permission:{} {}", sysrole, sparrowApi);
		assertEquals(1, apiService.getPermissions(sparrowApi.getId(), Pageable.unpaged()).getSize());
		apiService.delPermissions(sysroleApiPKs);
		log.info("del permission:{} {}", sysrole, sparrowApi);
		assertEquals(0, apiService.getPermissions(sparrowApi.getId(), Pageable.unpaged()).getSize());
		
	}

	@Test
	@Transactional
	void menuTest(){
		String prev= null;
		String prev1=null;
		for(int i =0;i<10;i++){
			Menu parent= menuServiceImpl.save(new Menu("m" + i, null));
			if(i>0){
				menuServiceImpl.setPosition(parent.getId(), prev, null);
			}
			prev=parent.getId();
			for(int j =0;j<10;j++){
				
				Menu child =menuServiceImpl.save(new Menu("m" + i + "" +j, parent.getId()));
				if(j>0){
					menuServiceImpl.setPosition(prev, prev1, null);
				}
				prev1=child.getId();
			}
		}

		assertEquals(110, menuServiceImpl.all(Pageable.unpaged(), null).getTotalElements());

		Sysrole sysrole = sysroleService.create(new Sysrole("testForMenu", "testForMenu"));
		
		//角色授权
		MenuPermission sysroleMenuPermission = new MenuPermission();
		List<SysroleMenuPK> sysroleMenuPKs = new ArrayList<>();
		Menu menu = menuRepository.findByCode("m0");
		sysroleMenuPKs.add(new SysroleMenuPK(sysrole.getId(), menu.getId()));
		sysroleMenuPermission.setSysroleMenuPKs(sysroleMenuPKs);
		menuServiceImpl.addPermission(sysroleMenuPermission);
		log.info("{} {}", menuServiceImpl, sysrole);
		SparrowTree<Menu,String> sparrowTree = menuServiceImpl.getTreeBySysroleId(sysrole.getId());
		assertEquals(1, sparrowTree.getChildren().size());
		assertEquals(sysrole, menuServiceImpl.getSysroles(menu.getId()).get(0));

		// 用户授权
		MenuPermission userMenuPermission = new MenuPermission();
		String username = "testUser";
		menu = menuRepository.findByCode("m1");
		List<UserMenuPK> userMenuPKs = new ArrayList<>();
		userMenuPKs.add(new UserMenuPK(username, menu.getId()));
		userMenuPermission.setUserMenuPKs(userMenuPKs);
		menuServiceImpl.addPermission(userMenuPermission);
		sparrowTree = menuServiceImpl.getTreeByUsername(username);
		assertEquals(1, sparrowTree.getChildren().size());
		assertEquals(username, menuServiceImpl.getUsers(menu.getId()).get(0));

		// 授予用户角色
		List<UserSysrolePK> userSysroles =new ArrayList<>();
		userSysroles.add(new UserSysrolePK(username, sysrole.getId()));
		sysroleServiceImpl.addPermissions(userSysroles);
		assertEquals(username,sysroleServiceImpl.getUsers(sysrole.getId()).get(0));
		
		log.info("{}" , userSysroleRepository.findByIdUsername(username));
		
		// 用户整个树
		sparrowTree = menuServiceImpl.getTreeByUsername(username);
		assertEquals(2, sparrowTree.getChildren().size());

		//取消角色授权
		menuServiceImpl.delPermission(sysroleMenuPermission);
		sparrowTree = menuServiceImpl.getTreeBySysroleId(sysrole.getId());
		assertEquals(0, sparrowTree.getChildren().size());
		assertEquals(0,menuServiceImpl.getSysroles(menu.getId()).size());

		// 取消用户授权
		menuServiceImpl.delPermission(userMenuPermission);
		sparrowTree = menuServiceImpl.getTreeBySysroleId(sysrole.getId());
		assertEquals(0, sparrowTree.getChildren().size());
		assertEquals(0,menuServiceImpl.getSysroles(menu.getId()).size());
	}
}

package cn.sparrow.permission.mgt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cn.sparrow.permission.constant.OrganizationTypeEnum;
import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.constant.PermissionExpressionEnum;
import cn.sparrow.permission.constant.PermissionTargetEnum;
import cn.sparrow.permission.core.api.AuditLogService;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.core.service.AuditLogServiceImpl;
import cn.sparrow.permission.core.service.PermissionServiceImpl;
import cn.sparrow.permission.mgt.api.ApiService;
import cn.sparrow.permission.mgt.api.EmployeeService;
import cn.sparrow.permission.mgt.api.MenuService;
import cn.sparrow.permission.mgt.api.ModelAttributeService;
import cn.sparrow.permission.mgt.api.ModelService;
import cn.sparrow.permission.mgt.api.OrganizationService;
import cn.sparrow.permission.mgt.api.SparrowPermissionTokenService;
import cn.sparrow.permission.mgt.api.SysroleService;
import cn.sparrow.permission.mgt.service.impl.ModelAttributeServiceImpl;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.resource.Menu;
import cn.sparrow.permission.model.resource.Model;
import cn.sparrow.permission.model.resource.ModelAttribute;
import cn.sparrow.permission.model.resource.ModelAttributePK;
import cn.sparrow.permission.model.resource.SparrowApi;
import cn.sparrow.permission.model.resource.SparrowTree;
import cn.sparrow.permission.model.resource.Sysrole;
import cn.sparrow.permission.model.resource.SysroleMenuPK;
import cn.sparrow.permission.model.resource.UserMenuPK;
import cn.sparrow.permission.model.token.MenuPermission;
import cn.sparrow.permission.model.token.PermissionExpression;
import cn.sparrow.permission.model.token.PermissionToken;
import cn.sparrow.permission.model.token.SparrowPermissionToken;
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
// @TestExecutionListeners({TransactionalTestExecutionListener.class})
public class ResourceTests {
	@Autowired
	private static TestEntityManager entityManager;


	@Autowired
	ApiService apiService;
	@Autowired
	MenuService menuService;
	@Autowired
	SysroleService sysroleService;
	@Autowired
	ModelService modelService;
	@Autowired
	ModelAttributeService modelAttributeService;

	

	@TestConfiguration
    static class PermissionServiceImplTestContextConfiguration {
        @Bean
        public PermissionService permissionService() {
            return new PermissionServiceImpl((EntityManager) entityManager);
        }
        
        @Bean
		public AuditLogService auditLogService() {
			return new AuditLogServiceImpl((EntityManager) entityManager);
		}
    }

	@Test
	void apiTest() throws Exception {
		Sysrole sysrole = new Sysrole("test", "test");
		assertNotNull(sysroleService.create(sysrole));
		SparrowApi sparrowApi = new SparrowApi("-","-",HttpMethodEnum.GET,"",ApiPermissionEnum.AUTHENTICATED);
		assertNotNull(apiService.create(sparrowApi));
		List<String> sysroleApiPKs =new ArrayList<>();
		sysroleApiPKs.add(sysrole.getId());
		apiService.addPermissions(sparrowApi.getId(), sysroleApiPKs);
		log.info("add permission:{} {}", sysrole, sparrowApi);
		assertEquals(1, apiService.getPermissions(sparrowApi.getId(), Pageable.unpaged()).getSize());
		apiService.removePermissions(sparrowApi.getId(), sysroleApiPKs);
		log.info("del permission:{} {}", sysrole, sparrowApi);
		assertEquals(0, apiService.getPermissions(sparrowApi.getId(), Pageable.unpaged()).getSize());
		
	}

	@Test
	@Transactional
	void menuTest(){
		String prev= null;
		String prev1=null;
		for(int i =0;i<10;i++){
			Menu parent= menuService.save(new Menu("m" + i, null));
			if(i>0){
				menuService.setPosition(parent.getId(), prev, null);
			}
			prev=parent.getId();
			for(int j =0;j<10;j++){
				
				Menu child =menuService.save(new Menu("m" + i + "" +j, parent.getId()));
				if(j>0){
					menuService.setPosition(prev, prev1, null);
				}
				prev1=child.getId();
			}
		}
		
		assertEquals(11, menuService.all(Pageable.unpaged(), new Menu("m1",null)).getTotalElements());
		assertEquals(110, menuService.all(Pageable.unpaged(), new Menu()).getTotalElements());

		Sysrole sysrole = sysroleService.create(new Sysrole("testForMenu", "testForMenu"));
		
		//角色授权
		MenuPermission sysroleMenuPermission = new MenuPermission();
		List<SysroleMenuPK> sysroleMenuPKs = new ArrayList<>();
		Menu menu1= new Menu();
		menu1.setCode("m0");
		Menu menu = menuService.all(Pageable.unpaged(), menu1).toList().get(0);
		sysroleMenuPKs.add(new SysroleMenuPK(sysrole.getId(), menu.getId()));
		sysroleMenuPermission.setSysroleMenuPKs(sysroleMenuPKs);
		menuService.addPermission(sysroleMenuPermission);
		log.info("{} {}", menuService, sysrole);
		SparrowTree<Menu,String> sparrowTree = menuService.getTreeBySysroleId(sysrole.getId());
		assertEquals(1, sparrowTree.getChildren().size());
		assertEquals(sysrole, menuService.getSysroles(menu.getId()).get(0));

		// 用户授权
		MenuPermission userMenuPermission = new MenuPermission();
		String username = "testUser";
		menu1.setCode("m1");
		menu = menuService.all(Pageable.unpaged(), menu1).toList().get(0);
		List<UserMenuPK> userMenuPKs = new ArrayList<>();
		userMenuPKs.add(new UserMenuPK(username, menu.getId()));
		userMenuPermission.setUserMenuPKs(userMenuPKs);
		menuService.addPermission(userMenuPermission);
		sparrowTree = menuService.getTreeByUsername(username);
		assertEquals(1, sparrowTree.getChildren().size());
		assertEquals(username, menuService.getUsers(menu.getId()).get(0));

		// 授予用户角色
		List<String> userSysroles =new ArrayList<>();
		userSysroles.add(username);
		sysroleService.addPermissions(sysrole.getId(),userSysroles);
		assertEquals(username,sysroleService.getUsers(sysrole.getId(), Pageable.unpaged()).toList().get(0).getId().getUsername());
		
//		log.info("{}" , userSysroleRepository.findByIdUsername(username));
		
		// 用户整个树
		sparrowTree = menuService.getTreeByUsername(username);
		assertEquals(2, sparrowTree.getChildren().size());

		//取消角色授权
		menuService.delPermission(sysroleMenuPermission);
		sparrowTree = menuService.getTreeBySysroleId(sysrole.getId());
		assertEquals(0, sparrowTree.getChildren().size());
		assertEquals(0,menuService.getSysroles(menu.getId()).size());

		// 取消用户授权
		menuService.delPermission(userMenuPermission);
		sparrowTree = menuService.getTreeBySysroleId(sysrole.getId());
		assertEquals(0, sparrowTree.getChildren().size());
		assertEquals(0,menuService.getSysroles(menu.getId()).size());

	}

	@Autowired
	ModelService modelServiceImpl;
	@Autowired
	EmployeeService employeeServiceImpl;
	@Autowired
	OrganizationService organizationServiceImpl;
	@Autowired
	SparrowPermissionTokenService sparrowPermissionTokenServiceImpl;

	@Test
	@Transactional
	void modelTest(){
		Model model = new Model(Organization.class.getName(), true);
		assertNotNull( modelServiceImpl.create(model));
		PermissionToken permissionToken = new PermissionToken();
		
		for (int i = 0; i <= 1; i++) {
			Employee employee = new Employee("testEmp" + i, "testEmp" +i,organizationServiceImpl.create(new Organization("testOrg" + i, "orgCode" + i, OrganizationTypeEnum.ORGANIZATION)).getId());
			employeeServiceImpl.create(employee);
			Map<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>> allowPermissions = new HashMap<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>>();
			Map<PermissionTargetEnum, List<PermissionExpression<?>>> targetMap = new HashMap<PermissionTargetEnum, List<PermissionExpression<?>>>();
			List<PermissionExpression<?>> expressions = new ArrayList<PermissionExpression<?>>();
			PermissionExpression<String> expression = new PermissionExpression<String>();
			List<String> ids = new ArrayList<String>();
			expression.setExpression(PermissionExpressionEnum.IN);
			ids.add(employee.getId());
			expression.setIds(ids);
			expressions.add(expression);
			targetMap.put(PermissionTargetEnum.EMPLOYEE, expressions);
			for (PermissionEnum permissionEnum : PermissionEnum.values()) {
				if (!permissionEnum.toString().contains("ALL")) {
					allowPermissions.put(permissionEnum, targetMap);
				}
			}
			if (i == 0) {
				permissionToken.setAllowPermissions(allowPermissions);
			} else {
				permissionToken.setDenyPermissions(allowPermissions);
			}
		}

		//设置模型权限
		SparrowPermissionToken sparrowPermissionToken = sparrowPermissionTokenServiceImpl.create(permissionToken);
		model.setSparrowPermissionToken(sparrowPermissionToken);
		modelServiceImpl.addPermission(model.getId(),permissionToken);
		assertNotNull(modelServiceImpl.getModel(model.getId()).getSparrowPermissionToken());
		// 删除模型权限
		modelServiceImpl.removePermission(model.getId());
		assertNull(modelServiceImpl.getModel(model.getId()).getSparrowPermissionToken());
	}

	@Autowired
	ModelAttributeService modelAttributeServiceImpl;

	@Test
	@Transactional
	void modelAttributeTest(){
		Model model = new Model(Organization.class.getName(), true);
		ModelAttribute modelAttribute = new ModelAttribute(new ModelAttributePK("attributeName", model.getId()));
		assertNotNull( modelServiceImpl.create(model));
		assertNotNull( modelAttributeServiceImpl.create(modelAttribute));
		PermissionToken permissionToken = new PermissionToken();
		
		for (int i = 0; i <= 1; i++) {
			Employee employee = new Employee("testEmp" + i, "testEmp" +i,organizationServiceImpl.create(new Organization("testOrg" + i, "orgCode" + i, OrganizationTypeEnum.ORGANIZATION)).getId());
			employeeServiceImpl.create(employee);
			Map<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>> allowPermissions = new HashMap<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>>();
			Map<PermissionTargetEnum, List<PermissionExpression<?>>> targetMap = new HashMap<PermissionTargetEnum, List<PermissionExpression<?>>>();
			List<PermissionExpression<?>> expressions = new ArrayList<PermissionExpression<?>>();
			PermissionExpression<String> expression = new PermissionExpression<String>();
			List<String> ids = new ArrayList<String>();
			expression.setExpression(PermissionExpressionEnum.IN);
			ids.add(employee.getId());
			expression.setIds(ids);
			expressions.add(expression);
			targetMap.put(PermissionTargetEnum.EMPLOYEE, expressions);
			for (PermissionEnum permissionEnum : PermissionEnum.values()) {
				if (!permissionEnum.toString().contains("ALL")) {
					allowPermissions.put(permissionEnum, targetMap);
				}
			}
			if (i == 0) {
				permissionToken.setAllowPermissions(allowPermissions);
			} else {
				permissionToken.setDenyPermissions(allowPermissions);
			}
		}

		// 设置属性权限
		SparrowPermissionToken sparrowPermissionToken = sparrowPermissionTokenServiceImpl.create(permissionToken);
		modelAttribute.setSparrowPermissionToken(sparrowPermissionToken);
		modelAttributeServiceImpl.addPermission(modelAttribute.getId(),permissionToken);
		assertNotNull(modelAttributeServiceImpl.get(modelAttribute.getId()).getSparrowPermissionToken());
		// 移除属性权限
		modelAttributeServiceImpl.removePermission(modelAttribute.getId());
		assertNull(modelAttributeServiceImpl.get(modelAttribute.getId()).getSparrowPermissionToken());
	}
}

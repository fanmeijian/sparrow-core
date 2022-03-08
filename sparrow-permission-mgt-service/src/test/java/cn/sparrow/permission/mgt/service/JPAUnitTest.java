package cn.sparrow.permission.mgt.service;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceProperty;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import cn.sparrow.permission.constant.OrganizationTypeEnum;
import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.constant.PermissionExpressionEnum;
import cn.sparrow.permission.constant.PermissionTargetEnum;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.EmployeeUser;
import cn.sparrow.permission.model.organization.EmployeeUserPK;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationRelation;
import cn.sparrow.permission.model.resource.Model;
import cn.sparrow.permission.model.token.PermissionExpression;
import cn.sparrow.permission.model.token.PermissionToken;
import cn.sparrow.permission.model.token.SparrowPermissionToken;
import eu.drus.jpa.unit.api.JpaUnit;
import eu.drus.jpa.unit.api.TransactionMode;
import eu.drus.jpa.unit.api.Transactional;

//@RunWith(SpringRunner.class)
//@EnableAutoConfiguration
////@DataJpaTest
//@Import(JpaConfig.class)
//@WithMockUser(username = "user2")
//@ContextConfiguration(classes = { PermissionServiceImpl.class, PermissionExpression.class,
//		PermissionExpressionServiceImpl.class, PermissionExpressionServiceOrganization.class, OrganizationHelper.class,
//		PermissionTokenServiceImpl.class, EmployeeTokenServiceImpl.class, AuditingEntityListener.class })
@ExtendWith(JpaUnit.class)
@Transactional(TransactionMode.DISABLED)
public class JPAUnitTest {
//	@Rule
//    public EntityManagerProvider provider = EntityManagerProvider.withUnit("cn.sparrow.permission.domain");

	PermissionService permissionService;

	@PersistenceContext(unitName = "cn.sparrow.permission.domain", properties = {
			@PersistenceProperty(name = "javax.persistence.provider", value = "org.hibernate.jpa.HibernatePersistenceProvider"),
			@PersistenceProperty(name = "javax.persistence.jdbc.url", value = "jdbc:h2:mem:spr;DB_CLOSE_DELAY=-1")
			})
	private EntityManager entityManager;

//	@PersistenceUnit
	EntityManagerFactory entityManagerFactory;

	@BeforeEach
	public void before() {
//		CurrentEntityManagerFactory.INSTANCE.set(entityManager.getEntityManagerFactory());
//		CurrentUser.INSTANCE.logIn("ROOT");
//		this.entityManager = PersistenceUtil.entityManager();
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.url", "jdbc:h2:mem:spr;DB_CLOSE_DELAY=-1");
		properties.put("javax.persistence.jdbc.driver", "org.h2.Driver");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("javax.persistence.provider", "org.hibernate.jpa.HibernatePersistenceProvider");

//		new HibernatePersistenceProvider().createEntityManagerFactory(persistenceUnitName, properties)
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cn.sparrow.permission.domain", properties);
//		entityManager = entityManagerFactory.createEntityManager();
		// 初始化组织架构
		String orgId1 = "";
		String orgId2 = "";
		String unit1 = "";
		String emp = "";
		entityManager.getTransaction().begin();
//		Organization organization = organizationRepository.save(new Organization("总公司", "010000",OrganizationTypeEnum.ORGANIZATION));
		Organization organization = new Organization("总公司", "01", OrganizationTypeEnum.ORGANIZATION);
		entityManager.persist(organization);
		orgId1 = organization.getId();
		organization = new Organization("部门一", "01-01", OrganizationTypeEnum.UNIT);
		entityManager.persist(organization);
		unit1 = organization.getId();
		Employee employee = new Employee("张三", "00001", orgId1);
		entityManager.persist(employee);
		emp = employee.getId();
		employee = new Employee("李四", "00002", orgId1);
		entityManager.persist(employee);

		OrganizationRelation organizationRelation = new OrganizationRelation(unit1, orgId1);
		entityManager.persist(organizationRelation);

		organization = new Organization("部门二", "01-02", OrganizationTypeEnum.UNIT);
		entityManager.persist(organization);
		unit1 = organization.getId();
		organizationRelation = new OrganizationRelation(unit1, orgId1);
		entityManager.persist(organizationRelation);
		organization = new Organization("部门三", "01-03", OrganizationTypeEnum.UNIT);
		entityManager.persist(organization);
		unit1 = organization.getId();
		organizationRelation = new OrganizationRelation(unit1, orgId1);
		entityManager.persist(organizationRelation);

		organization = new Organization("分公司一", "0101", OrganizationTypeEnum.ORGANIZATION);
		entityManager.persist(organization);
		orgId2 = organization.getId();
		organizationRelation = new OrganizationRelation(orgId2, orgId1);
		entityManager.persist(organizationRelation);
		organization = new Organization("部门一", "0101-01", OrganizationTypeEnum.UNIT);
		entityManager.persist(organization);
		unit1 = organization.getId();
		organizationRelation = new OrganizationRelation(unit1, orgId2);
		entityManager.persist(organizationRelation);
		organization = new Organization("部门二", "0101-02", OrganizationTypeEnum.UNIT);
		entityManager.persist(organization);
		unit1 = organization.getId();
		organizationRelation = new OrganizationRelation(unit1, orgId2);
		entityManager.persist(organizationRelation);
		organization = new Organization("部门三", "0101-03", OrganizationTypeEnum.UNIT);
		entityManager.persist(organization);
		unit1 = organization.getId();
		organizationRelation = new OrganizationRelation(unit1, orgId2);
		entityManager.persist(organizationRelation);

		organization = new Organization("分公司二", "0102", OrganizationTypeEnum.ORGANIZATION);
		entityManager.persist(organization);
		orgId2 = organization.getId();
		organizationRelation = new OrganizationRelation(orgId2, orgId1);
		entityManager.persist(organizationRelation);
		organization = new Organization("部门一", "0102-01", OrganizationTypeEnum.UNIT);
		entityManager.persist(organization);
		unit1 = organization.getId();
		organizationRelation = new OrganizationRelation(unit1, orgId2);
		entityManager.persist(organizationRelation);
		organization = new Organization("部门二", "0102-02", OrganizationTypeEnum.UNIT);
		entityManager.persist(organization);
		unit1 = organization.getId();
		organizationRelation = new OrganizationRelation(unit1, orgId2);
		entityManager.persist(organizationRelation);
		organization = new Organization("部门三", "0102-03", OrganizationTypeEnum.UNIT);
		entityManager.persist(organization);
		unit1 = organization.getId();
		organizationRelation = new OrganizationRelation(unit1, orgId2);
		entityManager.persist(organizationRelation);

		organization = new Organization("分公司三", "0103", OrganizationTypeEnum.ORGANIZATION);
		entityManager.persist(organization);
		orgId2 = organization.getId();
		organizationRelation = new OrganizationRelation(orgId2, orgId1);
		entityManager.persist(organizationRelation);
		organization = new Organization("部门一", "0103-01", OrganizationTypeEnum.UNIT);
		entityManager.persist(organization);
		unit1 = organization.getId();
		organizationRelation = new OrganizationRelation(unit1, orgId2);
		entityManager.persist(organizationRelation);
		organization = new Organization("部门二", "0103-02", OrganizationTypeEnum.UNIT);
		entityManager.persist(organization);
		unit1 = organization.getId();
		organizationRelation = new OrganizationRelation(unit1, orgId2);
		entityManager.persist(organizationRelation);
		organization = new Organization("部门三", "0103-03", OrganizationTypeEnum.UNIT);
		entityManager.persist(organization);
		unit1 = organization.getId();
		organizationRelation = new OrganizationRelation(unit1, orgId2);
		entityManager.persist(organizationRelation);

		Map<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>> allowPermissions = new HashMap<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>>();
		Map<PermissionTargetEnum, List<PermissionExpression<?>>> targetMap = new HashMap<PermissionTargetEnum, List<PermissionExpression<?>>>();
		List<PermissionExpression<?>> expressions = new ArrayList<PermissionExpression<?>>();
		PermissionExpression<String> expression = new PermissionExpression<String>();
		List<String> ids = new ArrayList<String>();
		expression.setExpression(PermissionExpressionEnum.IN);
		ids.add(emp);
		expression.setIds(ids);
		expressions.add(expression);
		targetMap.put(PermissionTargetEnum.EMPLOYEE, expressions);
		allowPermissions.put(PermissionEnum.AUTHOR, targetMap);
//		String permissionTokenId = permissionTokenRepository.save(new SparrowPermissionToken(new PermissionToken(allowPermissions,null))).getId();

		Model model = new Model("cn.sparrow.permission.model.organization.Organization");
		SparrowPermissionToken sparrowPermissionToken = new SparrowPermissionToken(
				new PermissionToken(allowPermissions, null));
		entityManager.persist(sparrowPermissionToken);
		model.setSparrowPermissionToken(sparrowPermissionToken);
		entityManager.persist(model);
		String modelName = model.getName();
		EmployeeUser employeeUser = new EmployeeUser(new EmployeeUserPK("user1", emp));
		entityManager.persist(employeeUser);
		entityManager.getTransaction().commit();
//		entityManager.getTransaction().commit();
	}

	@Test
	public void should_find_no_tutorials_if_repository_is_empty() {
		System.out
				.println("-----------" + entityManager.createNamedQuery("EmployeeUser.findAll").getResultList().size());
//		CurrentUser.INSTANCE.logIn("user1");
		Organization organization = new Organization("asdffd", "dsafdf", OrganizationTypeEnum.UNIT);
//		System.out.println(organizationRepository.save(organization).getId());
		entityManager.persist(organization);
		assertNotNull(organization.getId());
	}

//	@Test
//	public void AuthorPermissionTest() {
//		assertTrue("权限检查失败", permissionService.hasPermission("1", "2", PermissionEnum.AUTHOR));
//
//	}
}
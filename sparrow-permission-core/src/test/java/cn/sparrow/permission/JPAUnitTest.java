package cn.sparrow.permission;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cn.sparrow.permission.constant.OrganizationTypeEnum;
import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.constant.PermissionExpressionEnum;
import cn.sparrow.permission.constant.PermissionTargetEnum;
import cn.sparrow.permission.model.Model;
import cn.sparrow.permission.model.SparrowPermissionToken;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.EmployeeUser;
import cn.sparrow.permission.model.organization.EmployeeUserPK;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationRelation;
import cn.sparrow.permission.model.organization.OrganizationRelationPK;
import cn.sparrow.permission.repository.ModelRepository;
import cn.sparrow.permission.repository.PermissionTokenRepository;
import cn.sparrow.permission.repository.organization.EmployeeRepository;
import cn.sparrow.permission.repository.organization.EmployeeUserRepository;
import cn.sparrow.permission.repository.organization.OrganizationRelationRepository;
import cn.sparrow.permission.repository.organization.OrganizationRepository;
import cn.sparrow.permission.service.EmployeeTokenServiceImpl;
import cn.sparrow.permission.service.OrganizationHelper;
import cn.sparrow.permission.service.PermissionExpression;
import cn.sparrow.permission.service.PermissionExpressionServiceImpl;
import cn.sparrow.permission.service.PermissionExpressionServiceOrganization;
import cn.sparrow.permission.service.PermissionService;
import cn.sparrow.permission.service.PermissionServiceImpl;
import cn.sparrow.permission.service.PermissionToken;
import cn.sparrow.permission.service.PermissionTokenServiceImpl;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@DataJpaTest
@Import(JpaConfig.class)
@WithMockUser(username = "user2")
@ContextConfiguration(classes = { PermissionServiceImpl.class, PermissionExpression.class,
		PermissionExpressionServiceImpl.class, PermissionExpressionServiceOrganization.class,
		OrganizationHelper.class, PermissionTokenServiceImpl.class, EmployeeTokenServiceImpl.class, AuditingEntityListener.class})
public class JPAUnitTest {
	@Autowired
	PermissionService permissionService;

	@Autowired
	OrganizationRepository organizationRepository;
	
	@Autowired
	OrganizationRelationRepository organizationRelationRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PermissionTokenRepository permissionTokenRepository;
	
	@Autowired
	ModelRepository modelRepository;
	
	@Autowired
	EmployeeUserRepository employeeUserRepository;

	@Before
	
	public void before() {
	
		// 初始化组织架构
		String orgId1="";
		String orgId2="";
		String unit1="";
		String emp="";
//		Organization organization = organizationRepository.save(new Organization("总公司", "010000",OrganizationTypeEnum.ORGANIZATION));
		orgId1 = organizationRepository.save(new Organization("总公司", "01",OrganizationTypeEnum.ORGANIZATION)).getId();
		unit1 = organizationRepository.save(organizationRepository.save(new Organization("部门一", "01-01",OrganizationTypeEnum.UNIT))).getId();
		emp = employeeRepository.save(new Employee("张三", "00001", orgId1)).getId();
		employeeRepository.save(new Employee("李四", "00002", orgId1)).getId();
		organizationRelationRepository.save(new OrganizationRelation(new OrganizationRelationPK(unit1, orgId1)));
		unit1 = organizationRepository.save(organizationRepository.save(new Organization("部门二", "01-02",OrganizationTypeEnum.UNIT))).getId();
		organizationRelationRepository.save(new OrganizationRelation(new OrganizationRelationPK(unit1, orgId1)));
		unit1 = organizationRepository.save(organizationRepository.save(new Organization("部门三", "01-03",OrganizationTypeEnum.UNIT))).getId();
		organizationRelationRepository.save(new OrganizationRelation(new OrganizationRelationPK(unit1, orgId1)));

		orgId2 = organizationRepository.save(new Organization("分公司一", "0101",OrganizationTypeEnum.ORGANIZATION)).getId();
		organizationRelationRepository.save(new OrganizationRelation(new OrganizationRelationPK(orgId2, orgId1)));
		unit1 = organizationRepository.save(organizationRepository.save(new Organization("部门一", "0101-01",OrganizationTypeEnum.UNIT))).getId();
		organizationRelationRepository.save(new OrganizationRelation(new OrganizationRelationPK(unit1, orgId2)));
		unit1 = organizationRepository.save(organizationRepository.save(new Organization("部门二", "0101-02",OrganizationTypeEnum.UNIT))).getId();
		organizationRelationRepository.save(new OrganizationRelation(new OrganizationRelationPK(unit1, orgId2)));
		unit1 = organizationRepository.save(organizationRepository.save(new Organization("部门三", "0101-03",OrganizationTypeEnum.UNIT))).getId();
		organizationRelationRepository.save(new OrganizationRelation(new OrganizationRelationPK(unit1, orgId2)));
		
		orgId2 = organizationRepository.save(new Organization("分公司二", "0102",OrganizationTypeEnum.ORGANIZATION)).getId();
		organizationRelationRepository.save(new OrganizationRelation(new OrganizationRelationPK(orgId2, orgId1)));
		unit1 = organizationRepository.save(organizationRepository.save(new Organization("部门一", "0102-01",OrganizationTypeEnum.UNIT))).getId();
		organizationRelationRepository.save(new OrganizationRelation(new OrganizationRelationPK(unit1, orgId2)));
		unit1 = organizationRepository.save(organizationRepository.save(new Organization("部门二", "0102-02",OrganizationTypeEnum.UNIT))).getId();
		organizationRelationRepository.save(new OrganizationRelation(new OrganizationRelationPK(unit1, orgId2)));
		unit1 = organizationRepository.save(organizationRepository.save(new Organization("部门三", "0102-03",OrganizationTypeEnum.UNIT))).getId();
		organizationRelationRepository.save(new OrganizationRelation(new OrganizationRelationPK(unit1, orgId2)));
		
		orgId2 = organizationRepository.save(new Organization("分公司三", "0103",OrganizationTypeEnum.ORGANIZATION)).getId();
		organizationRelationRepository.save(new OrganizationRelation(new OrganizationRelationPK(orgId2, orgId1)));
		unit1 = organizationRepository.save(organizationRepository.save(new Organization("部门一", "0103-01",OrganizationTypeEnum.UNIT))).getId();
		organizationRelationRepository.save(new OrganizationRelation(new OrganizationRelationPK(unit1, orgId2)));
		unit1 = organizationRepository.save(organizationRepository.save(new Organization("部门二", "0103-02",OrganizationTypeEnum.UNIT))).getId();
		organizationRelationRepository.save(new OrganizationRelation(new OrganizationRelationPK(unit1, orgId2)));
		unit1 = organizationRepository.save(organizationRepository.save(new Organization("部门三", "0103-03",OrganizationTypeEnum.UNIT))).getId();
		organizationRelationRepository.save(new OrganizationRelation(new OrganizationRelationPK(unit1, orgId2)));
		
		Map<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>> allowPermissions = new HashedMap();
		Map<PermissionTargetEnum, List<PermissionExpression<?>>> targetMap = new HashedMap();
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
		model.setSparrowPermissionToken(permissionTokenRepository.save(new SparrowPermissionToken(new PermissionToken(allowPermissions,null))));
		
		String modelName = modelRepository.save(model).getName();
		
		employeeUserRepository.save(new EmployeeUser(new EmployeeUserPK("user1",emp)));
	}

	@Test
	public void should_find_no_tutorials_if_repository_is_empty() {
		
		
		
		
		Organization organization = new Organization("asdffd","dsafdf", OrganizationTypeEnum.UNIT);
//		System.out.println(organizationRepository.save(organization).getId());
		assertNotNull(organizationRepository.save(organization));
	}

	@Test
	public void AuthorPermissionTest() {
		assertTrue("权限检查失败", permissionService.hasPermission("1", "2", PermissionEnum.AUTHOR));

	}
}
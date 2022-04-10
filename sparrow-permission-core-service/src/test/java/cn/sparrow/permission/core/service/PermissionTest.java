package cn.sparrow.permission.core.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import cn.sparrow.permission.constant.OrganizationTypeEnum;
import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.constant.PermissionExpressionEnum;
import cn.sparrow.permission.constant.PermissionTargetEnum;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationRelation;
import cn.sparrow.permission.model.resource.Model;
import cn.sparrow.permission.model.token.PermissionExpression;
import cn.sparrow.permission.model.token.PermissionToken;
import cn.sparrow.permission.model.token.SparrowPermissionToken;
import eu.drus.jpa.unit.api.JpaUnit;
import eu.drus.jpa.unit.api.TransactionMode;
import eu.drus.jpa.unit.api.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(JpaUnit.class)
@Transactional(TransactionMode.DISABLED)
public class PermissionTest {
	@PersistenceContext(unitName = "cn.sparrow.permission.domain1")
	private EntityManager entityManager;

	private String hasPermissionEmployeeId;
	private String noPermissionEmployeeId;
	private String denyPermissionEmployeeId;
	private String tokenId;

	@BeforeEach
	public void before() {
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
		employee.setUsername("user1");
		entityManager.persist(employee);
		hasPermissionEmployeeId = employee.getId();
		emp = employee.getId();
		employee = new Employee("李四", "00002", orgId1);
		entityManager.persist(employee);
		noPermissionEmployeeId = employee.getId();
		employee = new Employee("王五", "00003", orgId1);
		entityManager.persist(employee);
		denyPermissionEmployeeId = employee.getId();

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
		Model model = new Model("cn.sparrow.permission.model.organization.Organization");
		PermissionToken permissionToken = new PermissionToken();
		for (int i = 0; i <= 1; i++) {
			Map<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>> allowPermissions = new HashMap<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>>();
			Map<PermissionTargetEnum, List<PermissionExpression<?>>> targetMap = new HashMap<PermissionTargetEnum, List<PermissionExpression<?>>>();
			List<PermissionExpression<?>> expressions = new ArrayList<PermissionExpression<?>>();
			PermissionExpression<String> expression = new PermissionExpression<String>();
			List<String> ids = new ArrayList<String>();
			expression.setExpression(PermissionExpressionEnum.IN);
			if (i == 0) {
				ids.add(hasPermissionEmployeeId);
			} else {
				ids.add(denyPermissionEmployeeId);
			}
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

		SparrowPermissionToken sparrowPermissionToken = new SparrowPermissionToken(permissionToken);
		entityManager.persist(sparrowPermissionToken);
		model.setSparrowPermissionToken(sparrowPermissionToken);
		entityManager.persist(model);
		tokenId = sparrowPermissionToken.getId();
//		String permissionTokenId = permissionTokenRepository.save(new SparrowPermissionToken(new PermissionToken(allowPermissions,null))).getId();

	}

	@Test
	public void AuthorPermissionTest() {
		PermissionService permissionService = new PermissionServiceImpl(entityManager);
		for (PermissionEnum permissionEnum : PermissionEnum.values()) {
			if (!permissionEnum.toString().contains("ALL")) {
				assertTrue("权限检查失败", permissionService.hasPermission(hasPermissionEmployeeId, tokenId, permissionEnum));
				log.info("employee {} has permission {}", hasPermissionEmployeeId, permissionEnum);
				assertFalse("权限检查失败", permissionService.hasPermission(noPermissionEmployeeId, tokenId, permissionEnum));
				log.info("employee {} no permission {}", noPermissionEmployeeId, permissionEnum);
				assertFalse("权限检查失败",
						permissionService.hasPermission(denyPermissionEmployeeId, tokenId, permissionEnum));
				log.info("employee {} deny permission {}", denyPermissionEmployeeId, permissionEnum);
			}
		}
	}
}
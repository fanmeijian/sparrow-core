package cn.sparrow.permission;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.repository.organization.OrganizationRepository;
import cn.sparrow.permission.service.EmployeeTokenServiceImpl;
import cn.sparrow.permission.service.OrganizationHelper;
import cn.sparrow.permission.service.PermissionExpression;
import cn.sparrow.permission.service.PermissionExpressionServiceImpl;
import cn.sparrow.permission.service.PermissionExpressionServiceOrganization;
import cn.sparrow.permission.service.PermissionService;
import cn.sparrow.permission.service.PermissionServiceImpl;
import cn.sparrow.permission.service.PermissionTokenServiceImpl;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@DataJpaTest
@ContextConfiguration(classes = { PermissionServiceImpl.class, PermissionExpression.class,
		PermissionExpressionServiceImpl.class, PermissionExpressionServiceOrganization.class,
		OrganizationHelper.class, PermissionTokenServiceImpl.class, EmployeeTokenServiceImpl.class})
public class JPAUnitTest {
	@Autowired
	PermissionService permissionService;

	@Autowired
	OrganizationRepository organizationRepository;

	@Before
	public void before() {
		// 初始化组织架构
		
	}

	@Test
	public void should_find_no_tutorials_if_repository_is_empty() {
		Organization organization = new Organization();
		System.out.println(organizationRepository.save(organization).getId());
		assertNotNull(organizationRepository.save(organization));
	}

	@Test
	public void AuthorPermissionTest() {
		assertTrue("权限检查失败", permissionService.hasPermission("1", "2", PermissionEnum.AUTHOR));

	}
}
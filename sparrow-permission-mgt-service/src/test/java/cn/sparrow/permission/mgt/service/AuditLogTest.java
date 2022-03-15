package cn.sparrow.permission.mgt.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.sparrow.permission.constant.OrganizationTypeEnum;
import cn.sparrow.permission.core.api.AuditLogService;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.core.service.AuditLogServiceImpl;
import cn.sparrow.permission.core.service.PermissionServiceImpl;
import cn.sparrow.permission.mgt.api.AuditLogRestService;
import cn.sparrow.permission.mgt.api.OrganizationService;
import cn.sparrow.permission.mgt.api.RoleService;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationRole;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import cn.sparrow.permission.model.organization.Role;
import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@AutoConfigureTestDatabase
public class AuditLogTest {

//	@Autowired
//	private EntityManager entityManager;

	@Autowired
	AuditLogRestService auditLogRestService;

	@Autowired
	OrganizationService organizationService;
	@Autowired
	RoleService roleService;
////	
//	@Autowired
//	private LocalContainerEntityManagerFactoryBean entityManagerFactory;

	@TestConfiguration
	static class PermissionServiceImplTestContextConfiguration {

		@Bean
		public PermissionService permissionService(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
			return new PermissionServiceImpl(
					entityManagerFactory.getNativeEntityManagerFactory().createEntityManager());
		}

		@Bean
		public AuditLogService auditLogService(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
			return new AuditLogServiceImpl(entityManagerFactory.getNativeEntityManagerFactory().createEntityManager());
		}

	}

	@Test
	void logTest() throws Exception {

//		log.info("{}", entityManagerFactory.getNativeEntityManagerFactory().createEntityManager());
//		EntityManager entityManager = entityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
//		AuditLogService auditLogRestService1 = new AuditLogServiceImpl((EntityManager) entityManager);

		Organization organization = organizationService
				.create(new Organization("name", "code", OrganizationTypeEnum.UNIT));

		Role role = roleService.create(new Role("name", "code"));

		roleService.setParentOrg(role.getId(), Arrays.asList(new String[] { organization.getId() }));
		assertEquals(1,
				auditLogRestService
						.getLog(OrganizationRole.class.getName(),
								new ObjectMapper()
										.writeValueAsString(new OrganizationRolePK(organization.getId(), role.getId())))
						.size());
	}
}

package cn.sparrow.permission.mgt.config;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import cn.sparrow.permission.core.api.AuditLogService;
import cn.sparrow.permission.core.api.EmployeeTokenService;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.core.api.ScopePermission;
import cn.sparrow.permission.core.service.AuditLogServiceImpl;
import cn.sparrow.permission.core.service.EmployeeTokenServiceImpl;
import cn.sparrow.permission.core.service.ScopePermissionAspect;
import cn.sparrow.permission.core.service.PermissionServiceImpl;

@Configuration
public class SparrowConfig {

	@Autowired
	public SparrowConfig(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		LocalContainerEntityManagerFactoryBean o = (LocalContainerEntityManagerFactoryBean) entityManagerFactory;
		LocalContainerEntityManagerFactoryBean o1 = new LocalContainerEntityManagerFactoryBean();
		o1.setJpaVendorAdapter(o.getJpaVendorAdapter());
		o1.setDataSource(o.getDataSource());
		o1.setJpaPropertyMap(o.getJpaPropertyMap());
		o1.setPackagesToScan("cn.sparrow.permission.*");
		o1.afterPropertiesSet();
	}

	@Bean
	public PermissionService permissionService(EntityManager entityManager) {
		return new PermissionServiceImpl(entityManager);
	}

	@Bean
	public EmployeeTokenService employeeTokenService(EntityManager entityManager) {
		return new EmployeeTokenServiceImpl(entityManager);
	}

	@Bean
	public AuditLogService auditLogService(EntityManager entityManager) {
		return new AuditLogServiceImpl(entityManager);
	}
	
	@Bean
	public ScopePermissionAspect scopePermission() {
		return new ScopePermissionAspect();
	}
}

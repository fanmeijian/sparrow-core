package cn.sparrow.permission;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import cn.sparrow.permission.common.listener.CurrentEntityManagerFactory;
import cn.sparrow.permission.core.api.EmployeeTokenService;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.core.api.PermissionTokenService;
import cn.sparrow.permission.core.service.EmployeeTokenServiceImpl;
import cn.sparrow.permission.core.service.PermissionServiceImpl;
import cn.sparrow.permission.core.service.PermissionTokenServiceImpl;

@Configuration
public class EntityManagerFactoryConfiguration {

	@Autowired
	public EntityManagerFactoryConfiguration(LocalContainerEntityManagerFactoryBean entityManagerFactory) {

		Map<String, Object> map = new HashMap<String, Object>();
//		entityManagerFactory.getProperties().entrySet().forEach(f -> {
//			map.put(f.getKey(), f.getValue());
//		});
		LocalContainerEntityManagerFactoryBean o =(LocalContainerEntityManagerFactoryBean)entityManagerFactory;
		LocalContainerEntityManagerFactoryBean o1= new LocalContainerEntityManagerFactoryBean();
		o1.setJpaVendorAdapter(o.getJpaVendorAdapter());
		o1.setDataSource(o.getDataSource());
		o1.setJpaPropertyMap(o.getJpaPropertyMap());
		o1.setPackagesToScan("cn.sparrow.permission.model");
		o1.afterPropertiesSet();
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.url", "jdbc:h2:mem:spr;DB_CLOSE_DELAY=-1");
		properties.put("javax.persistence.jdbc.driver", "org.h2.Driver");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("javax.persistence.provider", "org.hibernate.jpa.HibernatePersistenceProvider");

//		new HibernatePersistenceProvider().createEntityManagerFactory(persistenceUnitName, properties)
		EntityManagerFactory entityManagerFactory1 = Persistence.createEntityManagerFactory("cn.sparrow.permission.domain", properties);
//		map.put("javax.persistence.provider", "org.hibernate.jpa.HibernatePersistenceProvider");
//		CurrentEntityManagerFactory.INSTANCE.set(Persistence.createEntityManagerFactory(
//				entityManagerFactory.getProperties().get("hibernate.ejb.persistenceUnitName").toString(),map));
		CurrentEntityManagerFactory.INSTANCE.set( entityManagerFactory1);
		System.out.println("*************" + CurrentEntityManagerFactory.INSTANCE.toString());
	}

	@Bean
	public PermissionTokenService permissionTokenService() {
		return new PermissionTokenServiceImpl();

	}

	@Bean
	public PermissionService permissionService() {
		return new PermissionServiceImpl();
	}

	@Bean
	public EmployeeTokenService employeeTokenService() {
		return new EmployeeTokenServiceImpl();
	}
}

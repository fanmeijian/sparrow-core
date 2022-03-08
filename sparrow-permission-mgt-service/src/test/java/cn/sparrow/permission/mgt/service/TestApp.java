package cn.sparrow.permission.mgt.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestApp {
	@PersistenceContext
	EntityManager entityManager;
 
	@Test
	public void test() {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.url", "jdbc:h2:mem:spr;DB_CLOSE_DELAY=-1");
		properties.put("javax.persistence.jdbc.driver", "org.h2.Driver");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.show_sql", "true");
		EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().createEntityManagerFactory("cn.sparrow.permission.domain", properties);

		System.out.println(entityManagerFactory);
	}
}

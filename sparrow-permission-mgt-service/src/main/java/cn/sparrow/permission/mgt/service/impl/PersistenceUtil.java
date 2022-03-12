package cn.sparrow.permission.mgt.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class PersistenceUtil {
	public static EntityManager entityManager() {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.url", "jdbc:h2:mem:spr;DB_CLOSE_DELAY=-1");
		properties.put("javax.persistence.jdbc.driver", "org.h2.Driver");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("javax.persistence.provider", "org.hibernate.jpa.HibernatePersistenceProvider");
		return Persistence.createEntityManagerFactory("cn.sparrow.permission.domain", properties).createEntityManager();
	}
}

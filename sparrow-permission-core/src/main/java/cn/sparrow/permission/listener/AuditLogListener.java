package cn.sparrow.permission.listener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EmbeddedId;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.SerializationUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.sparrow.permission.model.AuditLog;

@Component
public final class AuditLogListener {

//	@PersistenceContext(name = "cn.sparrow.permission.domain1")
	private EntityManager entityManager ;
	
	private EntityManagerFactory entityManagerFactory ;
	
	@Autowired
	private ObjectFactory<EntityManagerFactory> entityManagerObjectFactory;

	@PostPersist
	private void beforePersist(Object sparrowEntity) {
		saveAuditLog(sparrowEntity, "C");
	}

	@PostUpdate
	private void beforeUpdate(Object sparrowEntity) {
		saveAuditLog(sparrowEntity, "U");
	}

	@PreRemove
	private void beforeRemove(Object sparrowEntity) {
		saveAuditLog(sparrowEntity, "D");
	}

	public void saveAuditLog(Object sparrowEntity, String revtype) {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.url", "jdbc:h2:mem:spr;DB_CLOSE_DELAY=-1");
		properties.put("javax.persistence.jdbc.driver", "org.h2.Driver");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("javax.persistence.provider", "org.hibernate.jpa.HibernatePersistenceProvider");
		this.entityManagerFactory = Persistence.createEntityManagerFactory("cn.sparrow.permission.domain", properties);
		
//		this.entityManagerFactory = this.entityManagerObjectFactory.getObject();
		String id = null;
		try {
			List<Field> fieldList = new ArrayList<Field>();
			// fieldList.addAll(Arrays.asList(sparrowEntity.getClass().getSuperclass().getDeclaredFields()));
			// fieldList.addAll(Arrays.asList(sparrowEntity.getClass().getDeclaredFields()));
			ReflectionUtils.doWithFields(sparrowEntity.getClass(), f -> {
				fieldList.add(f);
			});
			Field[] fields = fieldList.toArray(new Field[] {});
			for (Field field : fields) {
				for (Annotation annotation : field.getDeclaredAnnotations()) {
					if (annotation.annotationType().equals(Id.class)
							|| annotation.annotationType().equals(EmbeddedId.class)) {
						String fieldName = field.getName();
						Method getMethod = sparrowEntity.getClass()
								.getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
						Object returnObj = getMethod.invoke(sparrowEntity, new Object[0]);
						id = new ObjectMapper().writeValueAsString(returnObj);
					}
				}
			}

		} catch (JsonProcessingException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		AuditLog auditLog = new AuditLog();
		auditLog.setObjectBytearray(SerializationUtils.serialize(sparrowEntity));
		auditLog.setModelName(sparrowEntity.getClass().getName());
		auditLog.setObjectId(id);
		auditLog.setTimestamp(new Date());
		auditLog.setRevtype(revtype);
		auditLog.setUsername(CurrentUser.INSTANCE.get());
		entityManager.getTransaction().begin();
		entityManager.persist(auditLog);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}

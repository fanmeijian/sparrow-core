package cn.sparrow.permission.listener;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;

import org.apache.commons.lang3.SerializationUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.sparrow.permission.model.AuditLog;

public final class AuditLogListener {

	private EntityManager entityManager;

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
//		this.entityManagerFactory = this.entityManagerObjectFactory.getObject();
		String id = null;
		try {
			List<Field> fieldList = new ArrayList<Field>();
			// fieldList.addAll(Arrays.asList(sparrowEntity.getClass().getSuperclass().getDeclaredFields()));
			// fieldList.addAll(Arrays.asList(sparrowEntity.getClass().getDeclaredFields()));
//			ReflectionUtils.doWithFields(sparrowEntity.getClass(), f -> {
//				fieldList.add(f);
//			});
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
		AuditLog auditLog = new AuditLog();
		auditLog.setObjectBytearray(SerializationUtils.serialize((Serializable) sparrowEntity));
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

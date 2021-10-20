package cn.sparrow.permission.listener;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;

import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.internal.marshaller.optimized.OptimizedMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.common.AuditLog;

@Component
public final class AuditLogListener {

	private static OptimizedMarshaller marsh;

	@Autowired
	public void setMarsh(OptimizedMarshaller marsh) {
		AuditLogListener.marsh = marsh;
	}

	
	static EntityManagerFactory entityManagerFactory;

	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		AuditLogListener.entityManagerFactory = entityManagerFactory;
	}

	@PostPersist
	private void beforePersist(AbstractSparrowEntity sparrowEntity) {
		saveAuditLog(sparrowEntity,"C");
	}
	
	@PostUpdate
	private void beforeUpdate(AbstractSparrowEntity sparrowEntity) {
		saveAuditLog(sparrowEntity,"U");
	}

	@PreRemove
	private void beforeRemove(AbstractSparrowEntity sparrowEntity) {		
		saveAuditLog(sparrowEntity,"D");
	}


	public void saveAuditLog(AbstractSparrowEntity sparrowEntity, String revtype) {
		try {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			AuditLog auditLog = new AuditLog();
			auditLog.setObjectBytearray(marsh.marshal(sparrowEntity));
			auditLog.setModelName(sparrowEntity.getClass().getName());
			auditLog.setObjectId(sparrowEntity.getId());
			auditLog.setTimestamp(new Date());
			auditLog.setRevtype(revtype);
			auditLog.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			entityManager.getTransaction().begin();
			entityManager.persist(auditLog);
			entityManager.getTransaction().commit();
			entityManager.close();
		} catch (IgniteCheckedException e) {
			e.printStackTrace();
		}
	}

}

package cn.sparrow.permission.core.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;

import cn.sparrow.permission.core.api.AuditLogService;

public class AuditLogServiceImpl implements AuditLogService {
	private EntityManager entityManager;

	@Override
	public List<?> getLog(Class<?> c, Object id) {
		AuditReader reader = AuditReaderFactory.get(entityManager);
		AuditQuery query = reader.createQuery().forRevisionsOfEntity(c, false, true);
		query.addOrder(AuditEntity.revisionNumber().desc());
		query.add(AuditEntity.id().eq(id));
		return query.getResultList();
	}

	public AuditLogServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<?> getLog(Class<?> c) {
		AuditReader reader = AuditReaderFactory.get(entityManager);
		AuditQuery query = reader.createQuery().forRevisionsOfEntity(c, false, true);
		query.addOrder(AuditEntity.revisionNumber().desc());
		query.add(AuditEntity.revisionType().eq(RevisionType.DEL));
		return query.getResultList();
	}

}

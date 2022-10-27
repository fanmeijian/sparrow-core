package cn.sparrow.permission.model;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.junit.jupiter.api.Test;

import cn.sparrow.permission.constant.OrganizationTypeEnum;
import cn.sparrow.permission.model.organization.Organization;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
//@ExtendWith(JpaUnit.class)
public class AuditLogTest {

	@PersistenceContext(unitName = "cn.sparrow.permission.domain")
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Transactional
	@Test
	public void test() {
		entityManager = Persistence.createEntityManagerFactory("cn.sparrow.permission.domain").createEntityManager();
		entityManager.getTransaction().begin();
		Organization organization = new Organization("test", "001", OrganizationTypeEnum.UNIT);
		entityManager.persist(organization);
		entityManager.getTransaction().commit();
		String idString =organization.getId();
//		log.info("organization: {}",organization);
		entityManager.getTransaction().begin();
		organization.setName("test2");
		entityManager.persist(new Organization("test", "001223", OrganizationTypeEnum.UNIT));
		entityManager.merge(organization);
		entityManager.getTransaction().commit();
		entityManager.getTransaction().begin();
		entityManager.remove(organization);
		entityManager.getTransaction().commit();

		AuditReader reader = AuditReaderFactory.get(entityManager);
		AuditQuery query = reader.createQuery().forRevisionsOfEntity(Organization.class, false, true);
//		query = reader.createQuery().forEntitiesAtRevision(Organization.class, 1);
//		log.info("rev for {} {}", Organization.class, query.getSingleResult());
		query.addOrder(AuditEntity.revisionNumber().desc());
		query.add(AuditEntity.id().eq(idString));
		assertEquals(3, query.getResultList().size());
//		query.getResultList().forEach(f->{
//			log.info("rev for {} {}", Organization.class, f);
//		});
//		
		

	}

}

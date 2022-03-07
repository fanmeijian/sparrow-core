package cn.sparrow.permission;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import cn.sparrow.permission.constant.OrganizationTypeEnum;
import cn.sparrow.permission.model.organization.Organization;
import eu.drus.jpa.unit.api.JpaUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(JpaUnit.class)
class AuditTest {

	@PersistenceContext(unitName = "cn.sparrow.permission.domain")
	EntityManager entityManager;

	@Test
	void AuditLotTest() {
		Organization organization = new Organization("test", "001", OrganizationTypeEnum.UNIT);
		entityManager.persist(organization);
		entityManager.getTransaction().commit();
		log.info("organization: {}",organization);
		entityManager.getTransaction().begin();
		organization.setName("test2");
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
		query.getResultList().forEach(f->{
			log.info("rev for {} {}", Organization.class, f);
		});
//		
		

	}

}

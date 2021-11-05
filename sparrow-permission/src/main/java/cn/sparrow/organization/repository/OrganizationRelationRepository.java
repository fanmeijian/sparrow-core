package cn.sparrow.organization.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import cn.sparrow.model.organization.OrganizationRelation;
import cn.sparrow.model.organization.OrganizationRelationPK;

@RepositoryRestResource(exported = false)
public interface OrganizationRelationRepository extends JpaRepository<OrganizationRelation, OrganizationRelationPK> {
  Set<OrganizationRelation> findByIdOrganizationId(String organizationId);
  Set<OrganizationRelation> findByIdParentId(String parentID);
  
  @Transactional
  void deleteByIdOrganizationIdInOrIdParentIdIn(String[] ids1,String[] ids2);
  int countByIdParentId(String id);
}

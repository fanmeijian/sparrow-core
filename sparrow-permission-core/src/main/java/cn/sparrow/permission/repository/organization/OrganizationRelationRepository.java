package cn.sparrow.permission.repository.organization;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.organization.OrganizationRelation;
import cn.sparrow.permission.model.organization.OrganizationRelationPK;

public interface OrganizationRelationRepository
    extends JpaRepository<OrganizationRelation, OrganizationRelationPK> {
  Set<OrganizationRelation> findByIdOrganizationId(String organizationId);

  Set<OrganizationRelation> findByIdParentId(String parentID);

  @Transactional
  void deleteByIdOrganizationIdInOrIdParentIdIn(String[] ids1, String[] ids2);

  long countByIdParentId(String id);

  long countByIdOrganizationId(String id);

  @Transactional
  void deleteByIdIn(Set<OrganizationRelationPK> ids);

  void deleteByIdOrganizationId(String organizationId);
}

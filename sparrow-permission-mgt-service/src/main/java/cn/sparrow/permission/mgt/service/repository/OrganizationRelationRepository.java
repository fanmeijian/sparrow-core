package cn.sparrow.permission.mgt.service.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.organization.OrganizationRelation;
import cn.sparrow.permission.model.organization.OrganizationRelationPK;

public interface OrganizationRelationRepository
    extends JpaRepository<OrganizationRelation, OrganizationRelationPK> {
  Set<OrganizationRelation> findByIdOrganizationId(String organizationId);
  List<OrganizationRelation> findByIdParentId(String parentId);
  Page<OrganizationRelation> findByIdParentId(String parentId, Pageable pageable);

  @Transactional
  void deleteByIdOrganizationIdInOrIdParentIdIn(String[] ids1, String[] ids2);

  long countByIdParentId(String id);

  long countByIdOrganizationId(String id);	

  @Transactional
  void deleteByIdIn(Set<OrganizationRelationPK> ids);

  void deleteByIdOrganizationId(String organizationId);
}

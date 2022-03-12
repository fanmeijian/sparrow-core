package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelation;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelationPK;

public interface OrganizationPositionLevelRelationRepository extends JpaRepository<OrganizationPositionLevelRelation, OrganizationPositionLevelRelationPK> {

	long countByIdParentId(OrganizationPositionLevelPK id);

}

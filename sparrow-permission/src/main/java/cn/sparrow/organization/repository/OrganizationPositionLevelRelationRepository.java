package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.model.organization.OrganizationPositionLevelRelation;
import cn.sparrow.model.organization.OrganizationPositionLevelRelationPK;

public interface OrganizationPositionLevelRelationRepository extends JpaRepository<OrganizationPositionLevelRelation, OrganizationPositionLevelRelationPK> {

	long countByIdParentId(OrganizationPositionLevelPK id);

}

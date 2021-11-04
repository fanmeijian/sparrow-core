package cn.sparrow.organization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.model.organization.OrganizationPositionLevelRelation;
import cn.sparrow.model.organization.OrganizationPositionLevelRelationPK;

public interface OrganizationLevelRelationRepository extends JpaRepository<OrganizationPositionLevelRelation,OrganizationPositionLevelRelationPK> {

	List<OrganizationPositionLevelRelation> findByIdParentId(OrganizationPositionLevelPK parentId);

	List<OrganizationPositionLevelRelation> findByIdId(OrganizationPositionLevelPK id);

}

package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelation;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelationPK;

public interface OrganizationLevelRelationRepository extends JpaRepository<OrganizationPositionLevelRelation,OrganizationPositionLevelRelationPK> {

	List<OrganizationPositionLevelRelation> findByIdParentId(OrganizationPositionLevelPK parentId);

	List<OrganizationPositionLevelRelation> findByIdId(OrganizationPositionLevelPK id);

}

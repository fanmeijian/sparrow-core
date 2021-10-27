package cn.sparrow.organization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.model.organization.OrganizationLevelPK;
import cn.sparrow.model.organization.OrganizationLevelRelation;
import cn.sparrow.model.organization.OrganizationLevelRelationPK;

public interface OrganizationLevelRelationRepository extends JpaRepository<OrganizationLevelRelation,OrganizationLevelRelationPK> {

	List<OrganizationLevelRelation> findByIdParentId(OrganizationLevelPK parentId);

}

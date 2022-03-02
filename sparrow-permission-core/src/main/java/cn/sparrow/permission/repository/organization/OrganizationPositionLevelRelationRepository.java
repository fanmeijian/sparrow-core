package cn.sparrow.permission.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelation;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelationPK;

@RepositoryRestResource(exported = false)
public interface OrganizationPositionLevelRelationRepository extends JpaRepository<OrganizationPositionLevelRelation, OrganizationPositionLevelRelationPK> {

	long countByIdParentId(OrganizationPositionLevelPK id);

}

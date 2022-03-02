package cn.sparrow.permission.repository.organization;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelation;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelRelationPK;

@RepositoryRestResource(exported = false)
public interface OrganizationLevelRelationRepository extends JpaRepository<OrganizationPositionLevelRelation,OrganizationPositionLevelRelationPK> {

	List<OrganizationPositionLevelRelation> findByIdParentId(OrganizationPositionLevelPK parentId);

	List<OrganizationPositionLevelRelation> findByIdId(OrganizationPositionLevelPK id);

}

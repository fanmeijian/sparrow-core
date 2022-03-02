package cn.sparrow.permission.repository.organization;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.permission.model.organization.OrganizationRolePK;
import cn.sparrow.permission.model.organization.OrganizationRoleRelation;
import cn.sparrow.permission.model.organization.OrganizationRoleRelationPK;

@RepositoryRestResource(exported = false)
public interface OrganizationRoleRelationRepository
		extends JpaRepository<OrganizationRoleRelation, OrganizationRoleRelationPK> {

	List<OrganizationRoleRelation> findByIdParentId(@NotNull OrganizationRolePK parentId);

	List<OrganizationRoleRelation> findByIdId(@NotNull OrganizationRolePK organizationRolePK);

	long countByIdParentId(OrganizationRolePK id);

}

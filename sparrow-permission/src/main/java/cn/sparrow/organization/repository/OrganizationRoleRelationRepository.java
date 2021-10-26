package cn.sparrow.organization.repository;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.organization.OrganizatioinRoleRelationPK;
import cn.sparrow.model.organization.OrganizationRolePK;
import cn.sparrow.model.organization.OrganizationRoleRelation;

@RepositoryRestResource(exported = false)
public interface OrganizationRoleRelationRepository extends JpaRepository<OrganizationRoleRelation, OrganizatioinRoleRelationPK> {

  List<OrganizationRoleRelation> findByIdParentId(@NotNull OrganizationRolePK parentId);

}

package cn.sparrow.organization.repository;

import java.util.List;
import javax.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.organization.OrganizationRole;
import cn.sparrow.model.organization.OrganizationRolePK;

@RepositoryRestResource(exported = false)
public interface OrganizationRoleRepository extends JpaRepository<OrganizationRole, OrganizationRolePK> {

  List<OrganizationRole> findByIdOrganizationId(@NotBlank String organizationId);

	long countByIdOrganizationId(String id);

}

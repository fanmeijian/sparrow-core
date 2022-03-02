package cn.sparrow.permission.repository.organization;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.organization.OrganizationRole;
import cn.sparrow.permission.model.organization.OrganizationRolePK;

public interface OrganizationRoleRepository extends JpaRepository<OrganizationRole, OrganizationRolePK> {

	List<OrganizationRole> findByIdOrganizationId(@NotBlank String organizationId);

	long countByIdOrganizationId(String id);

	List<OrganizationRole> findByIdRoleId(@NotBlank String roleId);

}

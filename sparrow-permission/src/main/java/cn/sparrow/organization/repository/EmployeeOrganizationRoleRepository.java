package cn.sparrow.organization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.organization.EmployeeOrganizationRole;
import cn.sparrow.model.organization.EmployeeOrganizationRolePK;
import cn.sparrow.model.organization.OrganizationRolePK;
@RepositoryRestResource(exported = false)
public interface EmployeeOrganizationRoleRepository extends JpaRepository<EmployeeOrganizationRole, EmployeeOrganizationRolePK> {

	List<EmployeeOrganizationRole> findByIdOrganizationRoleId(OrganizationRolePK organizationRoleId);

}

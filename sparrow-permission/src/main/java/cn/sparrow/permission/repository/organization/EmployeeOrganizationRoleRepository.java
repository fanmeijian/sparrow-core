package cn.sparrow.permission.repository.organization;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.permission.model.organization.EmployeeOrganizationRole;
import cn.sparrow.permission.model.organization.EmployeeOrganizationRolePK;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
@RepositoryRestResource(exported = false)
public interface EmployeeOrganizationRoleRepository extends JpaRepository<EmployeeOrganizationRole, EmployeeOrganizationRolePK> {

	List<EmployeeOrganizationRole> findByIdOrganizationRoleId(OrganizationRolePK organizationRoleId);

	List<EmployeeOrganizationRole> findByIdEmployeeId(String employeeId);

}

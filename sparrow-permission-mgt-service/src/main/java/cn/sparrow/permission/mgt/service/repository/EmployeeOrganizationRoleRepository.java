package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.organization.EmployeeOrganizationRole;
import cn.sparrow.permission.model.organization.EmployeeOrganizationRolePK;
import cn.sparrow.permission.model.organization.OrganizationRolePK;

public interface EmployeeOrganizationRoleRepository extends JpaRepository<EmployeeOrganizationRole, EmployeeOrganizationRolePK> {

	List<EmployeeOrganizationRole> findByIdOrganizationRoleId(OrganizationRolePK organizationRoleId);

	List<EmployeeOrganizationRole> findByIdEmployeeId(String employeeId);

}

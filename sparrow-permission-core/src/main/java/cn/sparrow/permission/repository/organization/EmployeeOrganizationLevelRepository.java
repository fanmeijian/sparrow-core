package cn.sparrow.permission.repository.organization;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.organization.EmployeeOrganizationLevel;
import cn.sparrow.permission.model.organization.EmployeeOrganizationLevelPK;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;

public interface EmployeeOrganizationLevelRepository extends JpaRepository<EmployeeOrganizationLevel, EmployeeOrganizationLevelPK> {

	List<EmployeeOrganizationLevel> findByIdOrganizationLevelId(OrganizationPositionLevelPK organizationLevelId);

	List<EmployeeOrganizationLevel> findByIdEmployeeId(String employeeId);

}

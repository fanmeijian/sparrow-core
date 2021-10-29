package cn.sparrow.organization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.organization.EmployeeOrganizationLevel;
import cn.sparrow.model.organization.EmployeeOrganizationLevelPK;
import cn.sparrow.model.organization.OrganizationLevelPK;

@RepositoryRestResource(exported = false)
public interface EmployeeOrganizationLevelRepository extends JpaRepository<EmployeeOrganizationLevel, EmployeeOrganizationLevelPK> {

	List<EmployeeOrganizationLevel> findByIdOrganizationLevelId(OrganizationLevelPK organizationLevelId);

	List<EmployeeOrganizationLevel> findByIdEmployeeId(String employeeId);

}

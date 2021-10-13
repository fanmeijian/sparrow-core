package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.organization.EmployeeOrganizationLevel;
import cn.sparrow.model.organization.EmployeeOrganizationLevelPK;

@RepositoryRestResource(exported = false)
public interface EmployeeOrganizationLevelRepository extends JpaRepository<EmployeeOrganizationLevel, EmployeeOrganizationLevelPK> {

}

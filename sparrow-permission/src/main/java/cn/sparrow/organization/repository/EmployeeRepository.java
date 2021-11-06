package cn.sparrow.organization.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.organization.Employee;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "employee-controller")
@RepositoryRestResource(exported = false)
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	Iterable<Employee> findByRoot(boolean b);

	@Transactional
	void deleteByIdIn(String[] ids);

	List<Employee> findByOrganizationId(@NotBlank String organizationId, Pageable pageable);
	
	Employee findByUsername(String username);

	long countByOrganizationId(String id);

}

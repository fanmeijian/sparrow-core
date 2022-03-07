package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.organization.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

	Iterable<Employee> findByIsRoot(boolean b);

	@Transactional
	void deleteByIdIn(String[] ids);

	List<Employee> findByOrganizationId(@NotBlank String organizationId, Pageable pageable);

	long countByOrganizationId(String id);

}

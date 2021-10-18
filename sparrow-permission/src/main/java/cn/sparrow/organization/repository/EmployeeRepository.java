package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.organization.Employee;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "employee-controller")
@RepositoryRestResource(exported = false)
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}

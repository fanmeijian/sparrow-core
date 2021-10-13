package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.Employee;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "employee-controller")
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}

package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}

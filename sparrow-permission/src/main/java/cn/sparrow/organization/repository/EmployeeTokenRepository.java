package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.SparrowEmployeeToken;
import cn.sparrow.organization.service.EmployeeToken;

public interface EmployeeTokenRepository extends JpaRepository<SparrowEmployeeToken, String> {
	EmployeeToken findOneByEmployeeId(String employeeId);

}

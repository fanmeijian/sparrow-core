package cn.sparrow.permission.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.organization.SparrowEmployeeToken;
import cn.sparrow.permission.service.EmployeeToken;

public interface EmployeeTokenRepository extends JpaRepository<SparrowEmployeeToken, String> {
	EmployeeToken findOneByEmployeeId(String employeeId);

}

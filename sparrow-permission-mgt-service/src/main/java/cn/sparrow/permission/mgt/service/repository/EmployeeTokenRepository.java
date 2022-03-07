package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.token.EmployeeToken;
import cn.sparrow.permission.model.token.SparrowEmployeeToken;

public interface EmployeeTokenRepository extends JpaRepository<SparrowEmployeeToken, String> {
	EmployeeToken findOneByEmployeeId(String employeeId);

}

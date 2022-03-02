package cn.sparrow.permission.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.permission.model.organization.SparrowEmployeeToken;
import cn.sparrow.permission.service.EmployeeToken;

@RepositoryRestResource(exported = false)
public interface EmployeeTokenRepository extends JpaRepository<SparrowEmployeeToken, String> {
	EmployeeToken findOneByEmployeeId(String employeeId);

}

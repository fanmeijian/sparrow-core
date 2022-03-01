package cn.sparrow.permission.repository.organization;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.organization.EmployeeUser;
import cn.sparrow.permission.model.organization.EmployeeUserPK;

public interface EmployeeUserRepository extends JpaRepository<EmployeeUser, EmployeeUserPK> {
  EmployeeUser findOneByIdUsername(String username);
  List<EmployeeUser> findByIdEmployeeId(String employeeId);
}

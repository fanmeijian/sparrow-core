package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.EmployeeUser;
import cn.sparrow.model.organization.EmployeeUserPK;

public interface EmployeeUserRepository extends JpaRepository<EmployeeUser, EmployeeUserPK> {
  EmployeeUser findOneByIdUsername(String username);
}

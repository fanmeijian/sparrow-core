package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.SparrowEmployeeToken;

public interface EmployeeTokenRepository extends JpaRepository<SparrowEmployeeToken, String> {

}

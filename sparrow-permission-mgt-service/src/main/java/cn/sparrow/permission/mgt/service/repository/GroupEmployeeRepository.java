package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.group.GroupEmployee;
import cn.sparrow.permission.model.group.GroupEmployeePK;

public interface GroupEmployeeRepository extends JpaRepository<GroupEmployee, GroupEmployeePK> {

	Page<GroupEmployee> findByIdGroupId(String groupId, Pageable pageable);

	Page<GroupEmployee> findByIdEmployeeId(String employeeId, Pageable pageable);

}

package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.group.GroupEmployee;
import cn.sparrow.permission.model.group.GroupEmployeePK;

public interface GroupEmployeeRepository extends JpaRepository<GroupEmployee, GroupEmployeePK> {

	List<GroupEmployee> findByIdGroupId(@NotBlank String groupId);
	List<GroupEmployee> findByIdEmployeeId(@NotBlank String employeeId);

}

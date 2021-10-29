package cn.sparrow.group.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.model.group.GroupEmployee;
import cn.sparrow.model.group.GroupEmployeePK;

public interface GroupEmployeeRepository extends JpaRepository<GroupEmployee, GroupEmployeePK> {

	List<GroupEmployee> findByIdGroupId(@NotBlank String groupId);

}

package cn.sparrow.permission.repository.group;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.permission.model.group.GroupEmployee;
import cn.sparrow.permission.model.group.GroupEmployeePK;

@RepositoryRestResource(exported = false)
public interface GroupEmployeeRepository extends JpaRepository<GroupEmployee, GroupEmployeePK> {

	List<GroupEmployee> findByIdGroupId(@NotBlank String groupId);

}

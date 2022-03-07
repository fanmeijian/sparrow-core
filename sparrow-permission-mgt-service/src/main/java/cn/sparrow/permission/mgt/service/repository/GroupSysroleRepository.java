package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.group.GroupSysrole;
import cn.sparrow.permission.model.group.GroupSysrolePK;

public interface GroupSysroleRepository extends JpaRepository<GroupSysrole, GroupSysrolePK> {

	List<GroupSysrole> findByIdGroupId(@NotBlank String groupId);

}

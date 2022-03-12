package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.group.GroupRole;
import cn.sparrow.permission.model.group.GroupRolePK;

public interface GroupRoleRepository extends JpaRepository<GroupRole, GroupRolePK> {

	Page<GroupRole> findByIdGroupId(String groupId, Pageable pageable);

}

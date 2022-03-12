package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.group.GroupSysrole;
import cn.sparrow.permission.model.group.GroupSysrolePK;

public interface GroupSysroleRepository extends JpaRepository<GroupSysrole, GroupSysrolePK> {

	Page<GroupSysrole> findByIdGroupId(String groupId, Pageable pageable);

}

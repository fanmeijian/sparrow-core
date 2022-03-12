package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.group.GroupUser;
import cn.sparrow.permission.model.group.GroupUserPK;

public interface GroupUserRepository extends JpaRepository<GroupUser, GroupUserPK> {

	Page<GroupUser> findByIdGroupId(String groupId, Pageable p);

}

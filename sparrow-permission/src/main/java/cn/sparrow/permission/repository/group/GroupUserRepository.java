package cn.sparrow.permission.repository.group;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.permission.model.group.GroupUser;
import cn.sparrow.permission.model.group.GroupUserPK;

@RepositoryRestResource(exported = false)
public interface GroupUserRepository extends JpaRepository<GroupUser, GroupUserPK> {

	Page<GroupUser> findByIdGroupId(String groupId, Pageable p);

}

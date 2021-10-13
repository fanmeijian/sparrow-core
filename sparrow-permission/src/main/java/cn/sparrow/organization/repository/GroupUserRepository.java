package cn.sparrow.organization.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.organization.GroupUser;
import cn.sparrow.model.organization.GroupUserPK;

@RepositoryRestResource(exported = false)
public interface GroupUserRepository extends JpaRepository<GroupUser, GroupUserPK> {

	Page<GroupUser> findByIdGroupId(String groupId, Pageable p);

}

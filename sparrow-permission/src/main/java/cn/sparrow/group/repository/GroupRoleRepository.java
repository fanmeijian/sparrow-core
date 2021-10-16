package cn.sparrow.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.group.GroupRole;
import cn.sparrow.model.group.GroupRolePK;

@RepositoryRestResource(exported = false)
public interface GroupRoleRepository extends JpaRepository<GroupRole, GroupRolePK> {

}

package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.organization.GroupRole;
import cn.sparrow.model.organization.GroupRolePK;

@RepositoryRestResource(exported = false)
public interface GroupRoleRepository extends JpaRepository<GroupRole, GroupRolePK> {

}

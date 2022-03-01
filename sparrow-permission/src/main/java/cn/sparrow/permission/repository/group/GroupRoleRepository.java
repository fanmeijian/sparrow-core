package cn.sparrow.permission.repository.group;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.permission.model.group.GroupRole;
import cn.sparrow.permission.model.group.GroupRolePK;

@RepositoryRestResource(exported = false)
public interface GroupRoleRepository extends JpaRepository<GroupRole, GroupRolePK> {

	List<GroupRole> findByIdGroupId(@NotBlank String groupId);

}

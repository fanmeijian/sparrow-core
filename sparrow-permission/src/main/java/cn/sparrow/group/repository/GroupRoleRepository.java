package cn.sparrow.group.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.group.GroupRole;
import cn.sparrow.model.group.GroupRolePK;

@RepositoryRestResource(exported = false)
public interface GroupRoleRepository extends JpaRepository<GroupRole, GroupRolePK> {

	List<GroupRole> findByIdGroupId(@NotBlank String groupId);

}

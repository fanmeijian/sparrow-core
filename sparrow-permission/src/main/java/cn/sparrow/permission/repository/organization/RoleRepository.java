package cn.sparrow.permission.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.organization.Role;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "role-controller")
public interface RoleRepository extends JpaRepository<Role, String> {

	@Transactional
	void deleteByIdIn(String[] ids);

	Iterable<Role> findByIsRoot(boolean b);

}

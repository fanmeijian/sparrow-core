package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.organization.Role;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "role-controller")
public interface RoleRepository extends JpaRepository<Role, String> {

	@Transactional
	void deleteByIdIn(String[] ids);

	Iterable<Role> findByIsRoot(boolean b);

}

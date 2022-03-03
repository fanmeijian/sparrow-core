package cn.sparrow.permission.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.organization.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

	@Transactional
	void deleteByIdIn(String[] ids);

	Iterable<Role> findByIsRoot(boolean b);

}

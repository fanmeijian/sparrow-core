package cn.sparrow.permission.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.organization.Role;

@RepositoryRestResource(exported = false)
public interface RoleRepository extends JpaRepository<Role, String> {

	@Transactional
	void deleteByIdIn(String[] ids);

	Iterable<Role> findByIsRoot(boolean b);

}

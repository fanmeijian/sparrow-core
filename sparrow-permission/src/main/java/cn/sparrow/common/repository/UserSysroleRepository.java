package cn.sparrow.common.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import cn.sparrow.model.sysrole.UserSysrole;
import cn.sparrow.model.sysrole.UserSysrolePK;

public interface UserSysroleRepository extends JpaRepository<UserSysrole, UserSysrolePK> {
	Page<UserSysrole> findByIdUsername(String username,Pageable p);
	
	@RestResource(exported = false)
	Set<UserSysrole> findByIdUsername(String username);
}

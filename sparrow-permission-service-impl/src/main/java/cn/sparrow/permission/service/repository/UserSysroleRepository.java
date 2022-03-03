package cn.sparrow.permission.service.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.UserSysrole;
import cn.sparrow.permission.model.UserSysrolePK;

public interface UserSysroleRepository extends JpaRepository<UserSysrole, UserSysrolePK> {
	Page<UserSysrole> findByIdUsername(String username,Pageable p);
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)//解决在检验编辑者权限的时候，与查询在同一个事务造成死循环调用问题。
	Set<UserSysrole> findByIdUsername(String username);

	@Transactional
	void deleteByIdIn(List<UserSysrolePK> userSysrolePKs);
}

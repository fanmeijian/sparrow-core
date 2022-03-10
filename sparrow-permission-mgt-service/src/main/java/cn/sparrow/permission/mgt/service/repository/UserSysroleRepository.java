package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.resource.UserSysrole;
import cn.sparrow.permission.model.resource.UserSysrolePK;

public interface UserSysroleRepository extends JpaRepository<UserSysrole, UserSysrolePK> {
	// @Transactional(propagation=Propagation.NOT_SUPPORTED)
	List<UserSysrole> findByIdUsername(String username);
	List<UserSysrole> findByIdSysroleId(String sysroleId);
	
//	@Transactional(propagation=Propagation.NOT_SUPPORTED)//解决在检验编辑者权限的时候，与查询在同一个事务造成死循环调用问题。
//	Set<UserSysrole> findByIdUsername(String username);

	@Transactional
	void deleteByIdIn(List<UserSysrolePK> userSysrolePKs);
}

package cn.sparrow.common.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.menu.SysroleMenu;
import cn.sparrow.model.menu.SysroleMenuPK;

public interface SysroleMenuRepository extends JpaRepository<SysroleMenu, SysroleMenuPK> {
	Set<SysroleMenu> findByIdSysroleId(String sysroleId);

	@Transactional
	void deleteByIdSysroleIdAndIdMenuIdIn(String sysroleId, List<String> menuIds);
}

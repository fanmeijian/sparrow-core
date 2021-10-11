package cn.sparrow.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.menu.SysroleMenu;
import cn.sparrow.model.menu.SysroleMenuPK;

public interface SysroleMenuRepository extends JpaRepository<SysroleMenu, SysroleMenuPK> {

}

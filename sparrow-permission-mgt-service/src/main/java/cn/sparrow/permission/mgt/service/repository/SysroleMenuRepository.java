package cn.sparrow.permission.mgt.service.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.resource.SysroleMenu;
import cn.sparrow.permission.model.resource.SysroleMenuPK;

public interface SysroleMenuRepository extends JpaRepository<SysroleMenu, SysroleMenuPK> {
  Set<SysroleMenu> findByIdSysroleId(String sysroleId);

  @Transactional
  void deleteByIdSysroleIdAndIdMenuIdIn(String sysroleId, List<String> menuIds);

  @Transactional
  void deleteByIdIn(List<SysroleMenuPK> ids);

  Set<SysroleMenu> findByIdMenuId(String menuId);

}

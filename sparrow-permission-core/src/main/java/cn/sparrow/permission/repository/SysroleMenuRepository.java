package cn.sparrow.permission.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.SysroleMenu;
import cn.sparrow.permission.model.SysroleMenuPK;

@RepositoryRestResource(exported = false)
public interface SysroleMenuRepository extends JpaRepository<SysroleMenu, SysroleMenuPK> {
  Set<SysroleMenu> findByIdSysroleId(String sysroleId);

  @Transactional
  void deleteByIdSysroleIdAndIdMenuIdIn(String sysroleId, List<String> menuIds);

  @Transactional
  void deleteByIdIn(List<SysroleMenuPK> ids);

  Set<SysroleMenu> findByIdMenuId(String menuId);

}

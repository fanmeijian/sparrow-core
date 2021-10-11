package cn.sparrow.common.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import cn.sparrow.model.menu.Menu;


public interface MenuRepository extends JpaRepository<Menu, String> {

  List<Menu> findByParentId(String parentId);

  List<Menu> findBySparrowAppId(Long appId);

//  @Query("SELECT DISTINCT s FROM Menu s "
//      + "WHERE s.id IN (SELECT um.id.menuId FROM UserMenu um WHERE um.id.username = ?1) "
//      + "OR s.id IN (SELECT rm.id.menuId FROM SysroleMenu rm WHERE rm.id.sysroleId IN "
//      + "(SELECT us.id.sysroleId FROM UserSysrole us WHERE us.id.username = ?1))")
  @Query("SELECT s FROM Menu s")
  List<Menu> menulist(String username);

}

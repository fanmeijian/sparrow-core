package cn.sparrow.common.repository;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.permission.UserMenu;
import cn.sparrow.model.permission.UserMenuPK;

@RepositoryRestResource(exported = false)
public interface UserMenuRepository extends JpaRepository<UserMenu, UserMenuPK> {

  @Transactional
  void deleteByIdUsernameAndIdMenuIdIn(String username, List<String> menuIds);

  Set<UserMenu> findByIdUsername(String username);
  
  @Transactional
  void deleteByIdIn(List<UserMenuPK> ids);
}

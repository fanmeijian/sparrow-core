package cn.sparrow.common.repository;

import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.menu.UserMenu;
import cn.sparrow.model.menu.UserMenuPK;

@RepositoryRestResource(exported = false)
public interface UserMenuRepository extends JpaRepository<UserMenu, UserMenuPK> {

  @Transactional
  void deleteByIdUsernameAndIdMenuIdIn(String username, List<String> menuIds);

  Set<UserMenu> findByIdUsername(String username);
}

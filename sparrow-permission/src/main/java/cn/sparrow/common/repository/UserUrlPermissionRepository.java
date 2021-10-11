package cn.sparrow.common.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.permission.UserUrlPermission;
import cn.sparrow.model.permission.UserUrlPermissionPK;

public interface UserUrlPermissionRepository
    extends JpaRepository<UserUrlPermission, UserUrlPermissionPK> {
  List<UserUrlPermission> findBySparrowUrlClientId(String clientId);

  List<UserUrlPermission> findBySparrowUrlClientIdAndIdUsername(String clientId, String username);
  List<UserUrlPermission> findBySparrowUrlClientIdAndIdUsernameNotIn(String clientId, String... username);
}

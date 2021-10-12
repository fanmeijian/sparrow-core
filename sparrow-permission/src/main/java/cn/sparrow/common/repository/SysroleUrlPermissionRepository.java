package cn.sparrow.common.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.permission.SysroleUrlPermission;
import cn.sparrow.model.permission.SysroleUrlPermissionPK;

public interface SysroleUrlPermissionRepository
    extends JpaRepository<SysroleUrlPermission, SysroleUrlPermissionPK> {

  List<SysroleUrlPermission> findByIdUrlId(String urlId);
}

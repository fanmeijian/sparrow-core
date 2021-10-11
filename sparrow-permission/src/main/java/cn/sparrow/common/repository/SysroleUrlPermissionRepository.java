package cn.sparrow.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.permission.SysroleUrPermission;
import cn.sparrow.model.permission.SysroleUrlPermissionPK;

public interface SysroleUrlPermissionRepository
    extends JpaRepository<SysroleUrPermission, SysroleUrlPermissionPK> {
}

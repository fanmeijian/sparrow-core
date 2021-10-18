package cn.sparrow.permission.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.SysroleUrlPermission;
import cn.sparrow.model.permission.SysroleUrlPermissionPK;

@RepositoryRestResource(exported = false)
public interface SysroleUrlPermissionRepository
    extends JpaRepository<SysroleUrlPermission, SysroleUrlPermissionPK> {

  List<SysroleUrlPermission> findByIdUrlId(String urlId);
}

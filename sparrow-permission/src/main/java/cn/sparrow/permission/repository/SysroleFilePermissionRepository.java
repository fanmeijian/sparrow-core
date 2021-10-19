package cn.sparrow.permission.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import cn.sparrow.model.permission.FilePermissionPK;
import cn.sparrow.model.permission.SysroleFilePermission;
import cn.sparrow.model.permission.SysroleFilePermissionPK;

@RepositoryRestResource(exported = false)
public interface SysroleFilePermissionRepository
    extends JpaRepository<SysroleFilePermission, SysroleFilePermissionPK> {

  int countByIdFilePermissionPK(FilePermissionPK target);

  @Transactional
  void deleteByIdIn(List<SysroleFilePermissionPK> sysroleFilePermissionPKs);

}

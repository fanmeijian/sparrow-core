package cn.sparrow.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.permission.FilePermissionPK;
import cn.sparrow.model.permission.UserFilePermission;
import cn.sparrow.model.permission.UserFilePermissionPK;

public interface UserFilePermissionRepository extends JpaRepository<UserFilePermission, UserFilePermissionPK> {
  Long countByIdFilePermissionPK(FilePermissionPK filePermissionPK);
}

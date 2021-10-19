package cn.sparrow.permission.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;
import cn.sparrow.model.permission.FilePermissionPK;
import cn.sparrow.model.permission.UserFilePermission;
import cn.sparrow.model.permission.UserFilePermissionPK;

@RepositoryRestResource(exported = false)
public interface UserFilePermissionRepository extends JpaRepository<UserFilePermission, UserFilePermissionPK> {
  Long countByIdFilePermissionPK(FilePermissionPK filePermissionPK);

  @Transactional
  void deleteByIdIn(List<UserFilePermissionPK> userFilePermissionPKs);
}

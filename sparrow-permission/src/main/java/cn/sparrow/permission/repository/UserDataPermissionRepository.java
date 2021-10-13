package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.UserDataPermission;
import cn.sparrow.model.permission.UserDataPermissionPK;

@RepositoryRestResource(exported = false)
public interface UserDataPermissionRepository extends AbstractDataPermissionRepository<UserDataPermission, UserDataPermissionPK> {

}

package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.UserDataFieldPermission;
import cn.sparrow.model.permission.UserDataFieldPermissionPK;

@RepositoryRestResource(exported = false)
public interface UserDataFieldPermissionRepository extends AbstractDataFieldPermissionRepository<UserDataFieldPermission, UserDataFieldPermissionPK> {

}

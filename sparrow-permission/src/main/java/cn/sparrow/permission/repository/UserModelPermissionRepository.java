package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.UserModelPermission;
import cn.sparrow.model.permission.UserModelPermissionPK;

@RepositoryRestResource(exported = false)
public interface UserModelPermissionRepository extends AbstractModelPermissionRepository<UserModelPermission, UserModelPermissionPK> {

}

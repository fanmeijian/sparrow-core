package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.UserModelAttributePermission;
import cn.sparrow.model.permission.UserModelAttributePermissionPK;

@RepositoryRestResource(exported = false)
public interface UserModelAttributePermissionRepository extends AbstractModelAttributePermissionRepository<UserModelAttributePermission, UserModelAttributePermissionPK> {

}

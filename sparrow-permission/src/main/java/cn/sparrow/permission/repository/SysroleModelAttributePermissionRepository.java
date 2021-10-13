package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.SysroleModelAttributePermission;
import cn.sparrow.model.permission.SysroleModelAttributePermissionPK;

@RepositoryRestResource(exported = false)
public interface SysroleModelAttributePermissionRepository extends AbstractModelAttributePermissionRepository<SysroleModelAttributePermission, SysroleModelAttributePermissionPK> {

}

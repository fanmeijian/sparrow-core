package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.SysroleModelPermission;
import cn.sparrow.model.permission.SysroleModelPermissionPK;

@RepositoryRestResource(exported = false)
public interface SysroleModelPermissionRepository
		extends AbstractModelPermissionRepository<SysroleModelPermission, SysroleModelPermissionPK> {

}

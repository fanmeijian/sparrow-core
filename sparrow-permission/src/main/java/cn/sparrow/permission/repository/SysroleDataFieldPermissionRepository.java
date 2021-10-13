package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.SysroleDataFieldPermission;
import cn.sparrow.model.permission.SysroleDataFieldPermissionPK;

@RepositoryRestResource(exported = false)
public interface SysroleDataFieldPermissionRepository extends AbstractDataFieldPermissionRepository<SysroleDataFieldPermission, SysroleDataFieldPermissionPK> {

}

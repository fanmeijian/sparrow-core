package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.SysroleDataPermission;
import cn.sparrow.model.permission.SysroleDataPermissionPK;

@RepositoryRestResource(exported = false)
public interface SysroleDataPermissionRepository extends AbstractDataPermissionRepository<SysroleDataPermission, SysroleDataPermissionPK> {

}

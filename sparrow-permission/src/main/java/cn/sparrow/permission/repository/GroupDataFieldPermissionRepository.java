package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.GroupDataFieldPermission;
import cn.sparrow.model.permission.GroupDataFieldPermissionPK;

@RepositoryRestResource(exported = false)
public interface GroupDataFieldPermissionRepository extends AbstractDataFieldPermissionRepository<GroupDataFieldPermission, GroupDataFieldPermissionPK> {

}

package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.group.GroupDataPermission;
import cn.sparrow.model.group.GroupDataPermissionPK;

@RepositoryRestResource(exported = false)
public interface GroupDataPermissionRepository extends AbstractDataPermissionRepository<GroupDataPermission, GroupDataPermissionPK> {

}

package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.group.GroupModelPermission;
import cn.sparrow.model.group.GroupModelPermissionPK;

@RepositoryRestResource(exported = false)
public interface GroupModelPermissionRepository extends AbstractModelPermissionRepository<GroupModelPermission, GroupModelPermissionPK> {

}

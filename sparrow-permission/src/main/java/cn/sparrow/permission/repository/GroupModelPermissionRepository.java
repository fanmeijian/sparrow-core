package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.GroupModelPermission;
import cn.sparrow.model.permission.GroupModelPermissionPK;

@RepositoryRestResource(exported = false)
public interface GroupModelPermissionRepository extends AbstractModelPermissionRepository<GroupModelPermission, GroupModelPermissionPK> {

}

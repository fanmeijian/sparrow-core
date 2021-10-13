package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.GroupModelAttributePermission;
import cn.sparrow.model.permission.GroupModelAttributePermissionPK;

@RepositoryRestResource(exported = false)
public interface GroupModelAttributePermissionRepository extends AbstractModelAttributePermissionRepository<GroupModelAttributePermission, GroupModelAttributePermissionPK> {

}

package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.group.GroupModelAttributePermission;
import cn.sparrow.model.group.GroupModelAttributePermissionPK;

@RepositoryRestResource(exported = false)
public interface GroupModelAttributePermissionRepository extends AbstractModelAttributePermissionRepository<GroupModelAttributePermission, GroupModelAttributePermissionPK> {

}

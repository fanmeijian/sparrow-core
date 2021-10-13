package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.OrganizationModelAttributePermission;
import cn.sparrow.model.permission.OrganizationModelAttributePermissionPK;

@RepositoryRestResource(exported = false)
public interface OrganizationModelAttributePermissionRepository extends AbstractModelAttributePermissionRepository<OrganizationModelAttributePermission, OrganizationModelAttributePermissionPK> {

}

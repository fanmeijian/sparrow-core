package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.OrganizationModelPermission;
import cn.sparrow.model.permission.OrganizationModelPermissionPK;

@RepositoryRestResource(exported = false)
public interface OrganizationModelPermissionRepository extends AbstractModelPermissionRepository<OrganizationModelPermission, OrganizationModelPermissionPK> {

}

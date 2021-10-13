package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.OrganizationDataFieldPermission;
import cn.sparrow.model.permission.OrganizationDataFieldPermissionPK;

@RepositoryRestResource(exported = false)
public interface OrganizationDataFieldPermissionRepository extends AbstractDataFieldPermissionRepository<OrganizationDataFieldPermission, OrganizationDataFieldPermissionPK> {

}

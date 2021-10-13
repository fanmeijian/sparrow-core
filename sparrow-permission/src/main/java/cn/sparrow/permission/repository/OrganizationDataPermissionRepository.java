package cn.sparrow.permission.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.permission.OrganizationDataPermission;
import cn.sparrow.model.permission.OrganizationDataPermissionPK;

@RepositoryRestResource(exported = false)
public interface OrganizationDataPermissionRepository extends AbstractDataPermissionRepository<OrganizationDataPermission, OrganizationDataPermissionPK> {

}

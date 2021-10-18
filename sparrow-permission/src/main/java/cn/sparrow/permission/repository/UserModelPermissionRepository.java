package cn.sparrow.permission.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.permission.UserModelPermission;
import cn.sparrow.model.permission.UserModelPermissionPK;

@RepositoryRestResource(exported = false)
public interface UserModelPermissionRepository extends AbstractModelPermissionRepository<UserModelPermission, UserModelPermissionPK> {
	@Transactional
	void deleteByIdIn(List<UserModelPermissionPK> ids);
}

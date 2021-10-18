package cn.sparrow.permission.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.permission.UserModelAttributePermission;
import cn.sparrow.model.permission.UserModelAttributePermissionPK;

@RepositoryRestResource(exported = false)
public interface UserModelAttributePermissionRepository extends AbstractModelAttributePermissionRepository<UserModelAttributePermission, UserModelAttributePermissionPK> {
	@Transactional
	void deleteByIdIn(List<UserModelAttributePermissionPK> ids);
}

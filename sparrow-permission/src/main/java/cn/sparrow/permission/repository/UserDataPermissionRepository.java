package cn.sparrow.permission.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.permission.UserDataPermission;
import cn.sparrow.model.permission.UserDataPermissionPK;

@RepositoryRestResource(exported = false)
public interface UserDataPermissionRepository extends AbstractDataPermissionRepository<UserDataPermission, UserDataPermissionPK> {

	@Transactional
	void deleteByIdIn(List<UserDataPermissionPK> userDataPermissionPKs);

}

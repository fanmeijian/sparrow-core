package cn.sparrow.permission.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.permission.SysroleModelPermission;
import cn.sparrow.model.permission.SysroleModelPermissionPK;

@RepositoryRestResource(exported = false)
public interface SysroleModelPermissionRepository
		extends AbstractModelPermissionRepository<SysroleModelPermission, SysroleModelPermissionPK> {

	@Transactional
	void deleteByIdIn(List<SysroleModelPermissionPK> ids);
}

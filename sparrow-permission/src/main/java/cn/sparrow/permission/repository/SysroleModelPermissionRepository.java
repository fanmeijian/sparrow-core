package cn.sparrow.permission.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.permission.SysroleModelPermission;
import cn.sparrow.model.permission.SysroleModelPermissionPK;
import io.swagger.v3.oas.annotations.tags.Tag;

@RepositoryRestResource(path = "sysroleModelPermissions")
@Tag(name = "model-controller")
public interface SysroleModelPermissionRepository
		extends AbstractModelPermissionRepository<SysroleModelPermission, SysroleModelPermissionPK> {

	@Transactional
	void deleteByIdIn(List<SysroleModelPermissionPK> ids);
}

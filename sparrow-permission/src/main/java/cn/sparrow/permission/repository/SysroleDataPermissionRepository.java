package cn.sparrow.permission.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.permission.SysroleDataPermission;
import cn.sparrow.model.permission.SysroleDataPermissionPK;

@RepositoryRestResource(exported = false)
public interface SysroleDataPermissionRepository extends AbstractDataPermissionRepository<SysroleDataPermission, SysroleDataPermissionPK> {

	@Transactional
	void deleteByIdIn(List<SysroleDataPermissionPK> sysroleDataPermissionPKs);

}

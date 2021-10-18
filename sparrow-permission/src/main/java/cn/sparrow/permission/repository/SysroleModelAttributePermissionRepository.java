package cn.sparrow.permission.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.permission.SysroleModelAttributePermission;
import cn.sparrow.model.permission.SysroleModelAttributePermissionPK;

@RepositoryRestResource(exported = false)
public interface SysroleModelAttributePermissionRepository extends AbstractModelAttributePermissionRepository<SysroleModelAttributePermission, SysroleModelAttributePermissionPK> {

	@Transactional
	void deleteByIdIn(List<SysroleModelAttributePermissionPK> sysroleModelAttributePermissionPKs);

}

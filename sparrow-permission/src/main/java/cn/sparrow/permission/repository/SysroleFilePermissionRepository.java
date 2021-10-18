package cn.sparrow.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.permission.FilePermissionPK;
import cn.sparrow.model.permission.SysroleFilePermission;
import cn.sparrow.model.permission.SysroleFilePermissionPK;

@RepositoryRestResource(exported = false)
public interface SysroleFilePermissionRepository extends JpaRepository<SysroleFilePermission, SysroleFilePermissionPK> {

	int countByIdFilePermissionPK(FilePermissionPK target);
	
}

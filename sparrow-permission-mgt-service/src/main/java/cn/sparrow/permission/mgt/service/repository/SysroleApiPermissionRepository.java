package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.resource.Sysrole;
import cn.sparrow.permission.model.resource.SysroleApiPK;
import cn.sparrow.permission.model.resource.SysroleApiPermission;

public interface SysroleApiPermissionRepository extends JpaRepository<SysroleApiPermission, SysroleApiPK> {

	Page<SysroleApiPermission> findByIdApiId(String ApiId, Pageable pageable);

	Page<SysroleApiPermission> findByIdApiIdIn(String[] ApiIds, Pageable pageable);

	@Query(value = "SELECT * FROM SysroleApiPermission WHERE apiId = ?1", countQuery = "SELECT count(*) FROM SysroleApiPermission WHERE apiId = ?1", nativeQuery = true)
	Page<Sysrole> findByApiId(String ApiId, Pageable pageable);

	@Transactional
	void deleteByIdIn(List<SysroleApiPK> ids);
}

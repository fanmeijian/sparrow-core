package cn.sparrow.permission.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.SysroleApiPK;
import cn.sparrow.permission.model.SysroleApiPermission;

@RepositoryRestResource(exported = false)
public interface SysroleApiPermissionRepository extends JpaRepository<SysroleApiPermission, SysroleApiPK> {

	Page<SysroleApiPermission> findByIdApiId(String ApiId, Pageable pageable);
	Page<SysroleApiPermission> findByIdApiIdIn(String[] ApiIds, Pageable pageable);
	List<SysroleApiPermission> findByIdApiId(String ApiId);
	
	@Transactional
	void deleteByIdIn(List<SysroleApiPK> ids);
}

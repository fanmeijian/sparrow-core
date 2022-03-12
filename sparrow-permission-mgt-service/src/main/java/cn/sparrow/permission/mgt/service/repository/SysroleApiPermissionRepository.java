package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.resource.Sysrole;
import cn.sparrow.permission.model.resource.SysroleApiPK;
import cn.sparrow.permission.model.resource.SysroleApi;

public interface SysroleApiPermissionRepository extends JpaRepository<SysroleApi, SysroleApiPK> {

	Page<SysroleApi> findByIdApiId(String apiId, Pageable pageable);

	Page<SysroleApi> findByIdApiIdIn(String[] ApiIds, Pageable pageable);

	@Query(value = "SELECT s.* FROM spr_sysrole_api sa left join spr_sysrole s on sa.sysrole_id = s.id WHERE sa.api_id = ?1", countQuery = "SELECT count(*) FROM spr_sysrole_api sp WHERE sa.api_id = ?1", nativeQuery = true)
	Page<Sysrole> findByApiId(String ApiId, Pageable pageable);

	@Transactional
	void deleteByIdIn(List<SysroleApiPK> ids);
}

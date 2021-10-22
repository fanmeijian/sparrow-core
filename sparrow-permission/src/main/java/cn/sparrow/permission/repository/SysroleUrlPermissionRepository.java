package cn.sparrow.permission.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.permission.SysroleUrlPermission;
import cn.sparrow.model.permission.SysroleUrlPermissionPK;

@RepositoryRestResource(exported = false)
public interface SysroleUrlPermissionRepository extends JpaRepository<SysroleUrlPermission, SysroleUrlPermissionPK> {

	Page<SysroleUrlPermission> findByIdUrlId(String urlId, Pageable pageable);
	Page<SysroleUrlPermission> findByIdUrlIdIn(String[] urlIds, Pageable pageable);
	List<SysroleUrlPermission> findByIdUrlId(String urlId);
	
	@Transactional
	void deleteByIdIn(List<SysroleUrlPermissionPK> ids);
}

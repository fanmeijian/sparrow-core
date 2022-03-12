package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.resource.SysroleScope;
import cn.sparrow.permission.model.resource.SysroleScopePK;

public interface SysroleScopeRepository extends JpaRepository<SysroleScope, SysroleScopePK> {

	Page<SysroleScope> findByIdScopeId(String scopeId, Pageable pageable);

}

package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.resource.Scope;

public interface ScopeRepository extends JpaRepository<Scope, String> {

}

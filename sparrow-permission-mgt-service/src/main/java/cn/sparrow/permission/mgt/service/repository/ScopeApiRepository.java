package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.resource.ScopeApi;
import cn.sparrow.permission.model.resource.ScopeApiPK;

public interface ScopeApiRepository extends JpaRepository<ScopeApi, ScopeApiPK> {
	List<ScopeApi> findByIdApiId(String apiId);
}

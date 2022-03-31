package cn.sparrow.permission.mgt.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.sparrow.permission.mgt.api.ApiService;
import cn.sparrow.permission.mgt.service.repository.ApiRepository;
import cn.sparrow.permission.mgt.service.repository.ScopeApiRepository;
import cn.sparrow.permission.mgt.service.repository.SysroleApiPermissionRepository;
import cn.sparrow.permission.mgt.service.repository.SysroleRepository;
import cn.sparrow.permission.model.resource.ScopeApi;
import cn.sparrow.permission.model.resource.ScopeApiPK;
import cn.sparrow.permission.model.resource.SparrowApi;
import cn.sparrow.permission.model.resource.SysroleApi;
import cn.sparrow.permission.model.resource.SysroleApiPK;

@Service
public class ApiServiceImpl implements ApiService {
	@Autowired
	ApiRepository apiRepository;
	@Autowired
	SysroleApiPermissionRepository sysroleApiPermissionRepository;
	@Autowired
	SysroleRepository sysroleRepository;
	@Autowired
	ScopeApiRepository scopeApiRepository;

	@Override
	@ResponseStatus(code = HttpStatus.CREATED)
	public SparrowApi create(SparrowApi sparrowApi) {
		return apiRepository.save(sparrowApi);
	}

	@Override
	public SparrowApi get(String id) {
		return apiRepository.findById(id).get();
	}

	@Override
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(List<String> ids) {
		apiRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public Page<SysroleApi> getPermissions(String apiId, Pageable pageable) {
		return sysroleApiPermissionRepository.findByIdApiId(apiId, pageable);
	}

	@Override
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void addPermissions(String apiId, List<String> sysroleIds) {
		sysroleIds.forEach(f -> {
			sysroleApiPermissionRepository.save(new SysroleApi(apiId, f));
		});
	}

	@Override
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removePermissions(String apiId, List<String> sysroleIds) {
		sysroleIds.forEach(f -> {
			sysroleApiPermissionRepository.deleteById(new SysroleApiPK(f, apiId));
		});

	}

	@Override
	public SparrowApi update(String apiId, Map<String, Object> map) {
		SparrowApi source = apiRepository.getById(apiId);
		PatchUpdateHelper.merge(source, map, SparrowApi.class);
		return apiRepository.save(source);
	}

	@Override
	public Page<SparrowApi> all(Pageable pageable, SparrowApi sparrowApi) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return apiRepository.findAll(Example.of(sparrowApi, matcher), pageable);
	}

	@Override
	public List<ScopeApi> getScopes(String apiId) {
		return scopeApiRepository.findByIdApiId(apiId);
		
	}

	@Override
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Transactional
	public void addScopes(String apiId, List<String> scopeIds) {
		scopeIds.forEach(scopeId->{
			scopeApiRepository.save(new ScopeApi(scopeId, apiId));
		});
	}

	@Override
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Transactional
	public void removeScopes(String apiId, List<String> scopeIds) {
		scopeIds.forEach(scopeId->{
			scopeApiRepository.deleteById(new ScopeApiPK(scopeId, apiId));
		});
	}

}

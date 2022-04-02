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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.sparrow.permission.mgt.api.ApiService;
import cn.sparrow.permission.mgt.api.scopes.ApiScope;
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
public class ApiServiceImpl extends AbstractPreserveScope implements ApiService, ApiScope {
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
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_API_CREATE + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public SparrowApi create(SparrowApi sparrowApi) {
		return apiRepository.save(sparrowApi);
	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_API_LIST + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public SparrowApi get(String id) {
		return apiRepository.findById(id).get();
	}

	@Override
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_API_DELETE + "') or hasRole('ROLE_" + ROLE_SUPER_SYSADMIN + "')")
	public void delete(List<String> ids) {
		apiRepository.deleteByIdIn(ids.toArray(new String[] {}));
	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_API_PEM_LIST + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public Page<SysroleApi> getPermissions(String apiId, Pageable pageable) {
		return sysroleApiPermissionRepository.findByIdApiId(apiId, pageable);
	}

	@Override
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_API_PEM_ADD + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public void addPermissions(String apiId, List<String> sysroleIds) {
		sysroleIds.forEach(f -> {
			sysroleApiPermissionRepository.save(new SysroleApi(apiId, f));
		});
	}

	@Override
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_API_PEM_REMOVE + "') or hasRole('ROLE_" + ROLE_SUPER_SYSADMIN
			+ "')")
	public void removePermissions(String apiId, List<String> sysroleIds) {
		sysroleIds.forEach(f -> {
			sysroleApiPermissionRepository.deleteById(new SysroleApiPK(f, apiId));
		});

	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_API_UPDATE + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public SparrowApi update(String apiId, Map<String, Object> map) {
		SparrowApi source = apiRepository.getById(apiId);
		PatchUpdateHelper.merge(source, map, SparrowApi.class);
		return apiRepository.save(source);
	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_API_LIST + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public Page<SparrowApi> all(Pageable pageable, SparrowApi sparrowApi) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return apiRepository.findAll(Example.of(sparrowApi, matcher), pageable);
	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_API_SCOPE_LIST + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public List<ScopeApi> getScopes(String apiId) {
		return scopeApiRepository.findByIdApiId(apiId);
	}

	@Override
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Transactional
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_API_SCOPE_ADD + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public void addScopes(String apiId, List<String> scopeIds) {
		scopeIds.forEach(scopeId -> {
			scopeApiRepository.save(new ScopeApi(scopeId, apiId));
		});
	}

	@Override
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Transactional
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_API_SCOPE_REMOVE + "') or hasRole('ROLE_" + ROLE_SUPER_SYSADMIN
			+ "')")
	public void removeScopes(String apiId, List<String> scopeIds) {
		scopeIds.forEach(scopeId -> {
			scopeApiRepository.deleteById(new ScopeApiPK(scopeId, apiId));
		});
	}

}

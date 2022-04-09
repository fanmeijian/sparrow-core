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

import cn.sparrow.permission.constant.SysPermissionTarget;
import cn.sparrow.permission.mgt.api.PreserveScope;
import cn.sparrow.permission.mgt.api.ScopeService;
import cn.sparrow.permission.mgt.api.scopes.ScopeScope;
import cn.sparrow.permission.mgt.service.repository.ScopeRepository;
import cn.sparrow.permission.mgt.service.repository.SysroleScopeRepository;
import cn.sparrow.permission.mgt.service.repository.UserScopeRepository;
import cn.sparrow.permission.model.resource.Scope;
import cn.sparrow.permission.model.resource.SysroleScope;
import cn.sparrow.permission.model.resource.SysroleScopePK;
import cn.sparrow.permission.model.resource.UserScope;
import cn.sparrow.permission.model.resource.UserScopePK;

@Service
public class ScopeServiceImpl extends AbstractPreserveScope implements ScopeService, ScopeScope {

	@Autowired
	ScopeRepository scopeRepository;
	@Autowired
	SysroleScopeRepository sysroleScopeRepository;
	@Autowired
	UserScopeRepository userScopeRepository;

	@Autowired
	PreserveScope[] preserveScopes;

	@Override
	@ResponseStatus(value = HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_CREATE + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public Scope create(Scope scope) {
		return scopeRepository.save(scope);
	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_UPDATE + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public Scope update(String scopeId, Map<String, Object> map) {
		Scope scope = scopeRepository.findById(scopeId).get();
		PatchUpdateHelper.merge(scope, map);
		return scopeRepository.save(scope);
	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_READ + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public Scope get(String id) {
		return scopeRepository.findById(id).get();
	}

	@Override
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_DELETE + "') or hasRole('ROLE_" + ROLE_SUPER_SYSADMIN + "')")
	public void delete(List<String> ids) {
		scopeRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_LIST + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public Page<Scope> all(Pageable pageable, Scope scope) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return scopeRepository.findAll(Example.of(scope, matcher), pageable);
	}

	@Override
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_PEM_LIST + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public Page<?> getPermissions(String scopeId, SysPermissionTarget type, Pageable pageable) {
		Page<?> page = null;
		switch (type) {
		case SYSROLE:
			page = sysroleScopeRepository.findByIdScopeId(scopeId, pageable);
			break;
		case USER:
			page = userScopeRepository.findByIdScopeId(scopeId, pageable);
			break;
		default:
			break;
		}
		return page;
	}

	@Override
	@Transactional
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_PEM_ADD + "') or hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public void addPermissions(String scopeId, SysPermissionTarget type, List<?> ids) {
		switch (type) {
		case SYSROLE:
			ids.forEach(f -> {
				sysroleScopeRepository.save(new SysroleScope(f.toString(), scopeId));
			});
			break;
		case USER:
			ids.forEach(f -> {
				userScopeRepository.save(new UserScope(f.toString(), scopeId));
			});
			break;
		default:
			break;
		}

	}

	@Override
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@Transactional
	@PreAuthorize("hasAuthority('SCOPE_" + SCOPE_ADMIN_PEM_REMOVE + "') or hasRole('ROLE_" + ROLE_SUPER_SYSADMIN + "')")
	public void removePermissions(String scopeId, SysPermissionTarget type, List<?> ids) {
		switch (type) {
		case SYSROLE:
			ids.forEach(f -> {
				sysroleScopeRepository.deleteById(new SysroleScopePK(f.toString(), scopeId));
			});
			break;
		case USER:
			ids.forEach(f -> {
				userScopeRepository.deleteById(new UserScopePK(f.toString(), scopeId));
			});
			break;
		default:
			break;
		}

	}

	@Override
	@PreAuthorize("hasRole('ROLE_" + ROLE_SYSADMIN + "')")
	public List<String> preserveScopes() {
		List<String> scopes = this.getScopes();
		for (PreserveScope preserveScope : preserveScopes) {
			scopes.addAll(preserveScope.getScopes());
		}
		return scopes;
	}

}

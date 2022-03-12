package cn.sparrow.permission.mgt.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.sparrow.permission.mgt.api.ScopeService;
import cn.sparrow.permission.mgt.service.repository.ScopeRepository;
import cn.sparrow.permission.mgt.service.repository.SysroleScopeRepository;
import cn.sparrow.permission.mgt.service.repository.UserScopeRepository;
import cn.sparrow.permission.model.resource.Scope;
import cn.sparrow.permission.model.resource.SysroleScope;
import cn.sparrow.permission.model.resource.SysroleScopePK;

@Service
public class ScopeServiceImpl implements ScopeService {

	@Autowired
	ScopeRepository scopeRepository;
	@Autowired
	SysroleScopeRepository sysroleScopeRepository;
	@Autowired
	UserScopeRepository userScopeRepository;

	@Override
	@ResponseStatus(value = HttpStatus.CREATED)
	public Scope create(Scope scope) {
		return scopeRepository.save(scope);
	}

	@Override
	@ResponseStatus(value = HttpStatus.PARTIAL_CONTENT)
	public Scope update(String scopeId, Map<String, Object> map) {
		Scope scope = scopeRepository.findById(scopeId).get();
		PatchUpdateHelper.merge(scope, map);
		return scopeRepository.save(scope);
	}

	@Override
	public Scope get(String id) {
		return scopeRepository.findById(id).get();
	}

	@Override
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(List<String> ids) {
		scopeRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public Page<Scope> all(Pageable pageable, Scope scope) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return scopeRepository.findAll(Example.of(scope, matcher), pageable);
	}

	@Override
	public Page<SysroleScope> getPermissions(String scopeId, Pageable pageable) {
		return sysroleScopeRepository.findByIdScopeId(scopeId, pageable);
	}

	@Override
	@Transactional
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addPermissions(String scopeId, List<String> sysroleIds) {
		sysroleIds.forEach(f->{
			sysroleScopeRepository.save(new SysroleScope(f,scopeId));
		});
	}

	@Override
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removePermissions(String scopeId, List<String> sysroleIds) {
		sysroleIds.forEach(f->{
			sysroleScopeRepository.deleteById(new SysroleScopePK(f,scopeId));
		});
	}

}

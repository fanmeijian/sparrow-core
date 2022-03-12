package cn.sparrow.permission.mgt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.mgt.api.ScopeService;
import cn.sparrow.permission.model.resource.Scope;
import cn.sparrow.permission.model.resource.SparrowApi;

@Service
public class ScopeServiceImpl implements ScopeService {

	@Override
	public Scope create(SparrowApi sparrowApi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scope update(String scopeId, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scope get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(List<String> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Scope> all(Pageable pageable, Scope scope) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Scope> getPermissions(String scopeId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPermissions(String scopeId, List<String> sysroleIds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePermissions(String scopeId, List<String> sysroleIds) {
		// TODO Auto-generated method stub

	}

}

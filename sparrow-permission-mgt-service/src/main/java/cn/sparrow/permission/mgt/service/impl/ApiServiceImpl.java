package cn.sparrow.permission.mgt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.sparrow.permission.mgt.api.ApiService;
import cn.sparrow.permission.mgt.service.repository.ApiRepository;
import cn.sparrow.permission.mgt.service.repository.SysroleApiPermissionRepository;
import cn.sparrow.permission.mgt.service.repository.SysroleRepository;
import cn.sparrow.permission.model.resource.SparrowApi;
import cn.sparrow.permission.model.resource.Sysrole;
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
	public Page<Sysrole> getPermissions(String apiId, Pageable pageable) {
		List<Sysrole> sysroles = new ArrayList<Sysrole>();
		sysroleApiPermissionRepository.findByIdApiId(apiId, pageable).forEach(f -> {
			sysroles.add(f.getSysrole());
		});
		return new PageImpl<>(sysroles);
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

}

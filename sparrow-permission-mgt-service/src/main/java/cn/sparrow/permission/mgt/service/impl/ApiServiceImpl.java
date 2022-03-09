package cn.sparrow.permission.mgt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.mgt.api.ApiService;
import cn.sparrow.permission.mgt.service.repository.ApiRepository;
import cn.sparrow.permission.mgt.service.repository.SysroleApiPermissionRepository;
import cn.sparrow.permission.mgt.service.repository.SysroleRepository;
import cn.sparrow.permission.model.resource.SparrowApi;
import cn.sparrow.permission.model.resource.Sysrole;
import cn.sparrow.permission.model.resource.SysroleApiPK;
import cn.sparrow.permission.model.resource.SysroleApiPermission;

@Service
public class ApiServiceImpl implements ApiService {
	@Autowired
	ApiRepository apiRepository;
	@Autowired
	SysroleApiPermissionRepository sysroleApiPermissionRepository;
	@Autowired
	SysroleRepository sysroleRepository;

	public int saveApis(List<SparrowApi> sparrowUrls) {
		return apiRepository.saveAll(sparrowUrls).size();
	}

	public SparrowApi updateApi(SparrowApi sparrowUrl) {
		return apiRepository.save(sparrowUrl);
	}

	public SparrowApi getUrl(String id) {
		return apiRepository.findById(id).get();
	}

	public void deleteByIds(List<String> ids) {

	}

	public void delUrls(List<String> sparrowUrls) {
		sparrowUrls.forEach(f -> {
			apiRepository.deleteById(f);
		});
	}

	public Page<Sysrole> getPermissions(String apiId, Pageable pageable) {
		return sysroleApiPermissionRepository.findByApiId(apiId, pageable);
	}

	public void addPermissions(List<SysroleApiPK> sysroleUrlPermissionPKs) {
		sysroleUrlPermissionPKs.forEach(f -> {
			sysroleApiPermissionRepository.save(new SysroleApiPermission(f));
		});
	}

	public void delPermissions(List<SysroleApiPK> sysroleUrlPermissionPKs) {
		sysroleApiPermissionRepository.deleteByIdIn(sysroleUrlPermissionPKs);
	}

	@Override
	public Page<SparrowApi> getPermissionByUrlId(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SparrowApi> add(List<SparrowApi> urls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Sysrole> getPermission(String apiId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPermission(List<SysroleApiPK> sysroleUrlPermissionPKs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delPermission(List<SysroleApiPK> sysroleUrlPermissionPKs) {
		// TODO Auto-generated method stub

	}

	@Override
	public SparrowApi updateApi(String apiId, SparrowApi sparrowApi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SparrowApi> all(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}

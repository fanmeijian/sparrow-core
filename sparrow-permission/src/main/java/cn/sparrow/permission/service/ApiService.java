package cn.sparrow.permission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.model.SparrowApi;
import cn.sparrow.permission.model.SysroleApiPK;
import cn.sparrow.permission.model.SysroleApiPermission;
import cn.sparrow.permission.repository.ApiRepository;
import cn.sparrow.permission.repository.SysroleApiPermissionRepository;

@Service
public class ApiService {
	@Autowired
	ApiRepository apiRepository;
	@Autowired
	SysroleApiPermissionRepository sysroleApiPermissionRepository;

	public int saveUrls(List<SparrowApi> sparrowUrls) {
		return apiRepository.saveAll(sparrowUrls).size();
	}

	public SparrowApi updateUrl(SparrowApi sparrowUrl) {
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

	public Page<?> getPermissions(String urlId, Pageable pageable) {
		if (urlId != null) {
			return sysroleApiPermissionRepository.findByIdApiId(urlId,pageable);
	
		} else {
			return sysroleApiPermissionRepository.findAll(pageable);
		}
	}

	public void addPermissions(List<SysroleApiPK> sysroleUrlPermissionPKs) {
		sysroleUrlPermissionPKs.forEach(f -> {
			sysroleApiPermissionRepository.save(new SysroleApiPermission(f));
		});
	}

	public void delPermissions(List<SysroleApiPK> sysroleUrlPermissionPKs) {
		sysroleApiPermissionRepository.deleteByIdIn(sysroleUrlPermissionPKs);
	}
	
}

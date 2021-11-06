package cn.sparrow.permission.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sparrow.model.permission.SparrowApi;
import cn.sparrow.permission.repository.ApiRepository;

@Service
public class UrlService {
	@Autowired
	ApiRepository urlRepository;
//	@Autowired
//	SysroleUrlPermissionRepository sysroleUrlPermissionRepository;

	public int saveUrls(List<SparrowApi> sparrowUrls) {
		return urlRepository.saveAll(sparrowUrls).size();
	}

	public SparrowApi updateUrl(SparrowApi sparrowUrl) {
		return urlRepository.save(sparrowUrl);
	}

	public SparrowApi getUrl(String id) {
		return urlRepository.findById(id).get();
	}

	public void deleteByIds(List<String> ids) {

	}

	public void delUrls(List<String> sparrowUrls) {
		sparrowUrls.forEach(f -> {
			urlRepository.deleteById(f);
		});
	}

//	public Page<?> getPermissions(String urlId, Pageable pageable) {
//		if (urlId != null) {
//			return sysroleUrlPermissionRepository.findByIdUrlId(urlId,pageable);
//		} else {
//			return sysroleUrlPermissionRepository.findAll(pageable);
//		}
//	}
//
//	public void addPermissions(List<SysroleUrlPermissionPK> sysroleUrlPermissionPKs) {
//		sysroleUrlPermissionPKs.forEach(f -> {
//			sysroleUrlPermissionRepository.save(new SysroleUrlPermission(f));
//		});
//	}
//
//	public void delPermissions(List<SysroleUrlPermissionPK> sysroleUrlPermissionPKs) {
//		sysroleUrlPermissionRepository.deleteByIdIn(sysroleUrlPermissionPKs);
//	}
//	
}

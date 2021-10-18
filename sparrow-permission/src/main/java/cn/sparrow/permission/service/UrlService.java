package cn.sparrow.permission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.sparrow.model.permission.SparrowUrl;
import cn.sparrow.model.permission.SysroleUrlPermission;
import cn.sparrow.model.permission.SysroleUrlPermissionPK;
import cn.sparrow.permission.repository.SysroleUrlPermissionRepository;
import cn.sparrow.permission.repository.UrlRepository;

@Service
public class UrlService {
	@Autowired
	UrlRepository urlRepository;
	@Autowired
	SysroleUrlPermissionRepository sysroleUrlPermissionRepository;

	public int saveUrls(List<SparrowUrl> sparrowUrls) {
		return urlRepository.saveAll(sparrowUrls).size();
	}

	public SparrowUrl updateUrl(SparrowUrl sparrowUrl) {
		return urlRepository.save(sparrowUrl);
	}

	public SparrowUrl getUrl(String id) {
		return urlRepository.findById(id).get();
	}

	public void deleteByIds(List<String> ids) {

	}

	public void delUrls(List<String> sparrowUrls) {
		sparrowUrls.forEach(f -> {
			urlRepository.deleteById(f);
		});
	}

	public Page<?> getPermissions(String urlId, Pageable pageable) {
		if (urlId != null) {
			return sysroleUrlPermissionRepository.findByIdUrlId(urlId,pageable);
		} else {
			return sysroleUrlPermissionRepository.findAll(pageable);
		}
	}

	public void addPermissions(List<SysroleUrlPermissionPK> sysroleUrlPermissionPKs) {
		sysroleUrlPermissionPKs.forEach(f -> {
			sysroleUrlPermissionRepository.save(new SysroleUrlPermission(f));
		});
	}

	public void delPermissions(List<SysroleUrlPermissionPK> sysroleUrlPermissionPKs) {
		sysroleUrlPermissionRepository.deleteByIdIn(sysroleUrlPermissionPKs);
	}

}

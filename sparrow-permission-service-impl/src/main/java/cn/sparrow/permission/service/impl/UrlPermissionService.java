package cn.sparrow.permission.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.sparrow.permission.repository.SysroleApiPermissionRepository;
import cn.sparrow.permission.constant.ApiPermissionEnum;
import cn.sparrow.permission.model.SparrowApi;
import cn.sparrow.permission.repository.ApiRepository;

@Service
public class UrlPermissionService {
	@Autowired
	ApiRepository urlRepository;

	@Autowired
	SysroleApiPermissionRepository sysroleUrlPermissionRepository;

	private static Logger logger = LoggerFactory.getLogger(UrlPermissionService.class);

	public Set<SparrowApi> getSparrowUrls() {
		return new HashSet<SparrowApi>();

	}
//
//	public void addSysroleUrlPermission(List<SysroleUrlPermissionPK> sysroleUrlPermissionPKs) {
//		sysroleUrlPermissionPKs.forEach(f -> {
//			sysroleUrlPermissionRepository.save(new SysroleUrlPermission(f));
//		});
//	}
//
//	@Transactional
//	public void delSysroleUrlPermission(List<SysroleUrlPermissionPK> sysroleUrlPermissionPKs) {
//		sysroleUrlPermissionPKs.forEach(f -> {
//			sysroleUrlPermissionRepository.delete(new SysroleUrlPermission(f));
//		});
//	}

	public List<SparrowApi> getUrlsByClientIdAndPermission(String clientId, ApiPermissionEnum permission) {
		return urlRepository.findByClientIdAndPermission(clientId, permission);
	}

//	public Set<SparrowUrl> getSparrowUrlsByClientId(String clientId) {
//		return new HashSet<SparrowUrl>();
//	}
//
//	public Set<SparrowUrl> getSparrowUrlsDenyByClientId(String clientId) {
//		Set<SparrowUrl> sparrowUrls = new HashSet<SparrowUrl>();
//
//		urlRepository.findByClientIdAndPermission(clientId, UrlPermissionEnum.DENY).forEach(f -> {
//			sparrowUrls.add(f);
//		});
//
//		return sparrowUrls;
//	}
//
//	public Set<SparrowUrl> getSparrowUrlsAnonymousByClientId(String clientId) {
//		Set<SparrowUrl> sparrowUrls = new HashSet<SparrowUrl>();
//
//		urlRepository.findByClientIdAndPermission(clientId, UrlPermissionEnum.ANONYMOUS).forEach(f -> {
//			sparrowUrls.add(f);
//		});
//
//		return sparrowUrls;
//	}
//
//	public Set<SparrowUrl> getSparrowUrlsAuthenticatedByClientId(String clientId) {
//		Set<SparrowUrl> sparrowUrls = new HashSet<SparrowUrl>();
//
//		urlRepository.findByClientIdAndPermission(clientId, UrlPermissionEnum.AUTHENTICATED).forEach(f -> {
//			sparrowUrls.add(f);
//		});
//
//		return sparrowUrls;
//	}
//
//	public Set<SparrowUrl> getSparrowUrlsRestrictByClientId(String clientId) {
//		Set<SparrowUrl> sparrowUrls = new HashSet<SparrowUrl>();
//
//		urlRepository.findByClientIdAndPermission(clientId, UrlPermissionEnum.RESTRICT).forEach(f -> {
//			sparrowUrls.add(f);
//		});
//
//		return sparrowUrls;
//	}

	public String[] getSysrolesByUrlId(String urlId) {
		List<String> list = new ArrayList<String>();
		sysroleUrlPermissionRepository.findByIdApiId(urlId).forEach(f -> {
			list.add(f.getSysrole().getCode());
		});
		return list.toArray(new String[] {});
	}

	public void init() {
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		LinkedHashMap<?, LinkedHashMap> response = (LinkedHashMap) restTemplate
				.getForObject("http://localhost:8091/api/profile", LinkedHashMap.class).get("_links");

		logger.debug(response.toString());

		response.forEach((k, v) -> {
			if (!k.toString().equals("self")) {
				urlRepository.save(new SparrowApi(k.toString(),
						v.get("href").toString().replace("http://localhost:8091/api/profile", "") + "/**",
						HttpMethod.GET, "sparrow", ApiPermissionEnum.AUTHENTICATED));
				urlRepository.save(new SparrowApi(k.toString(),
						v.get("href").toString().replace("http://localhost:8091/api/profile", "") + "/**",
						HttpMethod.POST, "sparrow", ApiPermissionEnum.RESTRICT));
				urlRepository.save(new SparrowApi(k.toString(),
						v.get("href").toString().replace("http://localhost:8091/api/profile", "") + "/**",
						HttpMethod.PUT, "sparrow", ApiPermissionEnum.DENY));
				urlRepository.save(new SparrowApi(k.toString(),
						v.get("href").toString().replace("http://localhost:8091/api/profile", "") + "/**",
						HttpMethod.PATCH, "sparrow", ApiPermissionEnum.RESTRICT));
				urlRepository.save(new SparrowApi(k.toString(),
						v.get("href").toString().replace("http://localhost:8091/api/profile", "") + "/**",
						HttpMethod.DELETE, "sparrow", ApiPermissionEnum.RESTRICT));
			}
		});
		logger.info("finished url init.");

	}

}

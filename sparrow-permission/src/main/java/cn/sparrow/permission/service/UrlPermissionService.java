package cn.sparrow.permission.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import cn.sparrow.common.repository.SysroleUrlPermissionRepository;
import cn.sparrow.common.repository.UrlRepository;
import cn.sparrow.model.permission.SparrowUrl;
import cn.sparrow.model.permission.SysroleUrlPermission;
import cn.sparrow.model.permission.SysroleUrlPermissionPK;
import cn.sparrow.model.permission.UrlPermissionEnum;

@Service
public class UrlPermissionService {
  @Autowired
  UrlRepository urlRepository;
  
  @Autowired SysroleUrlPermissionRepository sysroleUrlPermissionRepository;


  private static Logger logger = LoggerFactory.getLogger(UrlPermissionService.class);

  public Set<SparrowUrl> getSparrowUrls() {
    return new HashSet<SparrowUrl>();

  }
  
  public void addSysroleUrlPermission(List<SysroleUrlPermissionPK> sysroleUrlPermissionPKs) {
	  sysroleUrlPermissionPKs.forEach(f->{
		  sysroleUrlPermissionRepository.save(new SysroleUrlPermission(f));
	  });
  }
  
  @Transactional
  public void delSysroleUrlPermission(List<SysroleUrlPermissionPK> sysroleUrlPermissionPKs) {
	  sysroleUrlPermissionPKs.forEach(f->{
		  sysroleUrlPermissionRepository.delete(new SysroleUrlPermission(f));
	  });
  }
  


  public Set<SparrowUrl> getSparrowUrlsByClientId(String clientId) {
    return new HashSet<SparrowUrl>();
  }

  public Set<SparrowUrl> getSparrowUrlsDenyByClientId(String clientId) {
    Set<SparrowUrl> sparrowUrls = new HashSet<SparrowUrl>();

    urlRepository.findByClientIdAndPermission(clientId, UrlPermissionEnum.DENY).forEach(f -> {
      sparrowUrls.add(f);
    });

    return sparrowUrls;
  }

  public Set<SparrowUrl> getSparrowUrlsAnonymousByClientId(String clientId) {
    Set<SparrowUrl> sparrowUrls = new HashSet<SparrowUrl>();

    urlRepository.findByClientIdAndPermission(clientId, UrlPermissionEnum.ANONYMOUS).forEach(f -> {
      sparrowUrls.add(f);
    });

    return sparrowUrls;
  }

  public Set<SparrowUrl> getSparrowUrlsAuthenticatedByClientId(String clientId) {
    Set<SparrowUrl> sparrowUrls = new HashSet<SparrowUrl>();

    urlRepository.findByClientIdAndPermission(clientId, UrlPermissionEnum.ATHENTICATED)
        .forEach(f -> {
          sparrowUrls.add(f);
        });

    return sparrowUrls;
  }

  public Set<SparrowUrl> getSparrowUrlsRestrictByClientId(String clientId) {
    Set<SparrowUrl> sparrowUrls = new HashSet<SparrowUrl>();

    urlRepository.findByClientIdAndPermission(clientId, UrlPermissionEnum.RESTRICT).forEach(f -> {
      sparrowUrls.add(f);
    });

    return sparrowUrls;
  }
  
  public String[] getSysrolesByUrlId(String urlId) {
    List<String> list = new ArrayList<String>();
    sysroleUrlPermissionRepository.findByIdUrlId(urlId).forEach(f->{
      list.add(f.getId().getSysroleId());
    });
    return list.toArray(new String[] {});
  }

  public void init() {
    RestTemplate restTemplate = new RestTemplate();
    LinkedHashMap<?, LinkedHashMap> response = (LinkedHashMap) restTemplate
        .getForObject("http://localhost:8091/api/profile", LinkedHashMap.class).get("_links");

    logger.debug(response.toString());

    response.forEach((k, v) -> {
      if(!k.toString().equals("self")) {
        urlRepository.save(new SparrowUrl(k.toString(),
            v.get("href").toString().replace("http://localhost:8091/api/profile", "") + "/**",
            HttpMethod.GET, "sparrow", UrlPermissionEnum.ATHENTICATED));
        urlRepository.save(new SparrowUrl(k.toString(),
            v.get("href").toString().replace("http://localhost:8091/api/profile", "") + "/**",
            HttpMethod.POST, "sparrow", UrlPermissionEnum.RESTRICT));
        urlRepository.save(new SparrowUrl(k.toString(),
            v.get("href").toString().replace("http://localhost:8091/api/profile", "") + "/**",
            HttpMethod.PUT, "sparrow", UrlPermissionEnum.DENY));
        urlRepository.save(new SparrowUrl(k.toString(),
            v.get("href").toString().replace("http://localhost:8091/api/profile", "") + "/**",
            HttpMethod.PATCH, "sparrow", UrlPermissionEnum.RESTRICT));
        urlRepository.save(new SparrowUrl(k.toString(),
            v.get("href").toString().replace("http://localhost:8091/api/profile", "") + "/**",
            HttpMethod.DELETE, "sparrow", UrlPermissionEnum.RESTRICT));
      }
    });
    logger.info("finished url init.");

  }

}

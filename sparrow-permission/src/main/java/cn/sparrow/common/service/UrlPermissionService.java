package cn.sparrow.common.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sparrow.common.repository.UrlRepository;
import cn.sparrow.common.repository.UserUrlPermissionRepository;
import cn.sparrow.model.url.SparrowUrl;

@Service
public class UrlPermissionService {
  @Autowired
  UrlRepository urlRepository;

  @Autowired
  UserUrlPermissionRepository userUrlPermissionRepository;

  public Set<SparrowUrl> getSparrowUrls() {
    return new HashSet<SparrowUrl>();

  }

  public Set<SparrowUrl> getSparrowUrlsByClientId(String clientId) {
    return new HashSet<SparrowUrl>();
  }

  public Set<SparrowUrl> getSparrowUrlsDenyByClientId(String clientId) {
    Set<SparrowUrl> sparrowUrls = new HashSet<SparrowUrl>();

    userUrlPermissionRepository.findBySparrowUrlClientIdAndIdUsername(clientId, "deny")
        .forEach(f -> {
          sparrowUrls.add(f.getSparrowUrl());
        });

    return sparrowUrls;
  }

  public Set<SparrowUrl> getSparrowUrlsAnonymousByClientId(String clientId) {
    Set<SparrowUrl> sparrowUrls = new HashSet<SparrowUrl>();

    userUrlPermissionRepository.findBySparrowUrlClientIdAndIdUsername(clientId, "anonymous")
        .forEach(f -> {
          sparrowUrls.add(f.getSparrowUrl());
        });

    return sparrowUrls;
  }

  public Set<SparrowUrl> getSparrowUrlsAuthenticatedByClientId(String clientId) {
    Set<SparrowUrl> sparrowUrls = new HashSet<SparrowUrl>();

    userUrlPermissionRepository.findBySparrowUrlClientIdAndIdUsername(clientId, "authenticated")
        .forEach(f -> {
          sparrowUrls.add(f.getSparrowUrl());
        });

    return sparrowUrls;
  }

  public Set<SparrowUrl> getSparrowUrlsRestrictByClientId(String clientId) {
    Set<SparrowUrl> sparrowUrls = new HashSet<SparrowUrl>();

    userUrlPermissionRepository.findBySparrowUrlClientIdAndIdUsernameNotIn(clientId,new String[] {"anonymous","authenticated","deny"})
        .forEach(f -> {
          sparrowUrls.add(f.getSparrowUrl());
        });

    return sparrowUrls;
  }

}

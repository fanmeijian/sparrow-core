package cn.sparrow.common.repository;


import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.url.SparrowUrl;
import cn.sparrow.model.url.UrlPermissionEnum;

public interface UrlRepository extends JpaRepository<SparrowUrl, String> {

  Set<SparrowUrl> findByClientId(String clientId);
  Set<SparrowUrl> findByClientIdAndPermission(String clientId, UrlPermissionEnum permission);
}

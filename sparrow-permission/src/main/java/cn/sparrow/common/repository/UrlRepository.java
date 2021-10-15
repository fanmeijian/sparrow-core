package cn.sparrow.common.repository;


import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.model.permission.SparrowUrl;
import cn.sparrow.model.permission.UrlPermissionEnum;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "url-controller")
public interface UrlRepository extends JpaRepository<SparrowUrl, String> {

  Set<SparrowUrl> findByClientId(String clientId);
  Set<SparrowUrl> findByClientIdAndPermission(String clientId, UrlPermissionEnum permission);
}

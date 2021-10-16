package cn.sparrow.common.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.model.common.UrlPermissionEnum;
import cn.sparrow.model.permission.SparrowUrl;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "url-controller")
public interface UrlRepository extends JpaRepository<SparrowUrl, String> {

  List<SparrowUrl> findByClientId(String clientId);
  List<SparrowUrl> findByClientIdAndPermission(String clientId, UrlPermissionEnum permission);
}

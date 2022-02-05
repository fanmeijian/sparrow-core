package cn.sparrow.permission.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.common.ApiPermissionEnum;
import cn.sparrow.model.permission.SparrowApi;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "api-controller")
public interface ApiRepository extends JpaRepository<SparrowApi, String> {

  List<SparrowApi> findByClientId(String clientId);
  List<SparrowApi> findByClientIdAndPermission(String clientId, ApiPermissionEnum permission);
  Page<SparrowApi> findByIdIn(String[] ids, Pageable unpaged);
  
  @Transactional
  void deleteByIdIn(String[] ids);
  
}

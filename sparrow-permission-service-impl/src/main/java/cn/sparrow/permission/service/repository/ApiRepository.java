package cn.sparrow.permission.service.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.constant.ApiPermissionEnum;
import cn.sparrow.permission.model.SparrowApi;

public interface ApiRepository extends JpaRepository<SparrowApi, String> {

  List<SparrowApi> findByClientId(String clientId);
  List<SparrowApi> findByClientIdAndPermission(String clientId, ApiPermissionEnum permission);
  Page<SparrowApi> findByIdIn(String[] ids, Pageable unpaged);
  
  @Transactional
  void deleteByIdIn(String[] ids);
  
}

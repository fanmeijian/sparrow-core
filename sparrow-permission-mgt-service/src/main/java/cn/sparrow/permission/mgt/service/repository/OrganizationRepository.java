package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.organization.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, String> {

  @Transactional
  void deleteByIdIn(String[] ids);

  List<Organization> findByIsRoot(boolean b);
  Page<Organization> findByIsRoot(boolean b, Pageable pageables);
}

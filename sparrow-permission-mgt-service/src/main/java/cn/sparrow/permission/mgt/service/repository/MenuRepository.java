package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.resource.Menu;

public interface MenuRepository extends JpaRepository<Menu, String> {

  List<Menu> findByParentId(String parentId);
  Menu findByCode(String code);

  @Transactional
  void deleteByIdIn(String[] ids);
}

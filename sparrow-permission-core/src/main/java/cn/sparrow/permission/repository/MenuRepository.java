package cn.sparrow.permission.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, String> {

  List<Menu> findByParentId(String parentId);

  @Transactional
  void deleteByIdIn(String[] ids);
}

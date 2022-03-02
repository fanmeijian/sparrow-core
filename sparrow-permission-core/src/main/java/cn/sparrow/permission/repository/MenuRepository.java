package cn.sparrow.permission.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.Menu;

@RepositoryRestResource(exported = false)
public interface MenuRepository extends JpaRepository<Menu, String> {

  List<Menu> findByParentId(String parentId);

  @Transactional
  @RestResource(exported = false)
  void deleteByIdIn(String[] ids);
}

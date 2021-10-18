package cn.sparrow.permission.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.permission.Menu;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "menu-controller")
@RepositoryRestResource(exported = false)
public interface MenuRepository extends JpaRepository<Menu, String> {

  List<Menu> findByParentId(String parentId);

//  List<Menu> findBySparrowAppId(Long appId);

}

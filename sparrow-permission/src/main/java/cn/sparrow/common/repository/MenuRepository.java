package cn.sparrow.common.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.model.permission.Menu;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "menu-controller")
public interface MenuRepository extends JpaRepository<Menu, String> {

  List<Menu> findByParentId(String parentId);

//  List<Menu> findBySparrowAppId(Long appId);

}

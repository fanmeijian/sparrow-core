package cn.sparrow.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.group.Group;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "group-controller")
public interface GroupRepository extends JpaRepository<Group, String> {

  @Transactional
  void deleteByIdIn(String[] ids);

}

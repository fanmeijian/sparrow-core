package cn.sparrow.permission.repository.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.group.Group;

public interface GroupRepository extends JpaRepository<Group, String> {

  @Transactional
  void deleteByIdIn(String[] ids);

}

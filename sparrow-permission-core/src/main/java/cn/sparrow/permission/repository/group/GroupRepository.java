package cn.sparrow.permission.repository.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.group.Group;

@RepositoryRestResource(exported = false)
public interface GroupRepository extends JpaRepository<Group, String> {

  @Transactional
  void deleteByIdIn(String[] ids);

}

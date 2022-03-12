package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.group.Group;

public interface GroupRepository extends JpaRepository<Group, String> ,JpaSpecificationExecutor<Group>{

  @Transactional
  void deleteByIdIn(String[] ids);
  Page<Group> findByNameContaining(String name, Pageable pageable);
}

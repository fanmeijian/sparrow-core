package cn.sparrow.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import antlr.collections.List;

@NoRepositoryBean
public interface TreeRepository<T, ID> extends JpaRepository<T, ID> {
  List findByParentId(String parentID);
}

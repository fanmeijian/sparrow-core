package cn.sparrow.common.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SparrowSingleRelationRepository<T, ID> extends JpaRepository<T, ID> {
  List<T> findByParentId(ID parentId);

  long countByParentId(ID parentId);
}

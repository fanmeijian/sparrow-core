package cn.sparrow.permission.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ModelPermissionRepository<T, ID> extends JpaRepository<T, ID> {
  Long countByModelNameAndUsername(String modelName, String username);

  Long countByModelName(String modelName);
  
  List<T> findAllByModelNameAndUsername(String modelName, String username);
}

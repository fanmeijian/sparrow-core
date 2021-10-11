package cn.sparrow.permission.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.permission.data.model.ModelAttribute;
import cn.sparrow.permission.data.model.ModelAttributePK;

public interface ModelAttributeRepository extends JpaRepository<ModelAttribute, ModelAttributePK> {
  Long countById(ModelAttributePK id);
}

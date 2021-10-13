package cn.sparrow.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.permission.ModelAttribute;
import cn.sparrow.model.permission.ModelAttributePK;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "model-attribute-controller")
public interface ModelAttributeRepository extends JpaRepository<ModelAttribute, ModelAttributePK> {
  Long countById(ModelAttributePK id);
}

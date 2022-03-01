package cn.sparrow.permission.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.ModelAttribute;
import cn.sparrow.permission.model.ModelAttributePK;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "model-attribute-controller")
@RepositoryRestResource(exported = false)
public interface ModelAttributeRepository extends JpaRepository<ModelAttribute, ModelAttributePK> {
  Long countById(ModelAttributePK id);
  
  @Transactional
  void deleteByIdIn(List<ModelAttributePK> ids);
}

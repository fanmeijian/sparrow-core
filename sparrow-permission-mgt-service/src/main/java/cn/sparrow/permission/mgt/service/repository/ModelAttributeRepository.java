package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.resource.ModelAttribute;
import cn.sparrow.permission.model.resource.ModelAttributePK;

public interface ModelAttributeRepository extends JpaRepository<ModelAttribute, ModelAttributePK> {
  Long countById(ModelAttributePK id);
  
  @Transactional
  void deleteByIdIn(List<ModelAttributePK> ids);
}

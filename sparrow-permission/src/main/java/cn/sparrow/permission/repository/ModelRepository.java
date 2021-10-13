package cn.sparrow.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.permission.Model;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "model-controller")
public interface ModelRepository extends JpaRepository<Model, String> {

}

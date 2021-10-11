package cn.sparrow.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.permission.Model;

public interface ModelRepository extends JpaRepository<Model, String> {

}

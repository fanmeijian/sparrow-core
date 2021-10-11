package cn.sparrow.permission.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.permission.data.model.Model;

public interface ModelRepository extends JpaRepository<Model, String> {

}

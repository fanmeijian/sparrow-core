package cn.sparrow.permission.repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.permission.model.app.FlowNo;

@RepositoryRestResource(exported = false)
public interface FlowNoRepository extends JpaRepository<FlowNo, String> {

}

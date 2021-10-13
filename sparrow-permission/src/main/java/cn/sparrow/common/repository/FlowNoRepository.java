package cn.sparrow.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.app.FlowNo;

@RepositoryRestResource(exported = false)
public interface FlowNoRepository extends JpaRepository<FlowNo, String> {

}
